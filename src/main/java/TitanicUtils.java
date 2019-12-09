import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.affinity.rendezvous.RendezvousAffinityFunction;
import org.apache.ignite.configuration.CacheConfiguration;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.Scanner;
import java.util.UUID;

public class TitanicUtils {
    /**
     * Read passengers data from csv file.
     *
     * @param ignite The ignite.
     * @return The filled cache.
     * @throws FileNotFoundException If data file is not found.
     */
    public static IgniteCache<Integer, Object[]> readPassengers(Ignite ignite)
        throws FileNotFoundException {
        IgniteCache<Integer, Object[]> cache = getCache(ignite);
        Scanner scanner = new Scanner(new File("/home/zaleslaw/data/titanic.csv"));

        int cnt = 0;
        while (scanner.hasNextLine()) {
            String row = scanner.nextLine();
            if(cnt == 0) {
                cnt++;
                continue;
            }
            String[] cells = row.split(";");
            Object[] data = new Object[cells.length];
            NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);

            for (int i = 0; i < cells.length; i++)
                try{
                    if(cells[i].equals("")) data[i] = Double.NaN;
                    else data[i] = Double.valueOf(cells[i]);
                } catch (NumberFormatException e) {

                    try {
                        data[i] = format.parse(cells[i]).doubleValue();
                    }
                    catch (ParseException e1) {
                        data[i] = cells[i];
                    }
                }
            cache.put(cnt++, data);
        }
        return cache;
    }

    /**
     * Fills cache with data and returns it.
     *
     * @param ignite Ignite instance.
     * @return Filled Ignite Cache.
     */
    private static IgniteCache<Integer, Object[]> getCache(Ignite ignite) {

        CacheConfiguration<Integer, Object[]> cacheConfiguration = new CacheConfiguration<Integer, Object[]>();
        cacheConfiguration.setName("TUTORIAL_" + UUID.randomUUID());
        cacheConfiguration.setAffinity(new RendezvousAffinityFunction(false, 10));

        return ignite.createCache(cacheConfiguration);
    }
}