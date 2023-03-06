/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version    2016.02.29
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer(String name)
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader(name);
    }
    
    public class Main {
        public static void main(String[] args) {
            LogfileCreator creator = new LogfileCreator();
            creator.createFile("entries.txt", 8);
            
            LogAnalyzer analyzer = new LogAnalyzer("entries.txt");
            analyzer.analyzeHourlyData();
            analyzer.printHourlyCounts();
            System.out.println(analyzer.numberOfAccesses());
        }
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        int hour = 0;
        while (hour < hourCounts.length) {
            System.out.println(hour + ": " + hourCounts[hour]);
            hour++;
        }
    }
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
    
    /**
     * Return the number of accesses recorded in the log file.
     * 
     */
    public int numberOfAccesses() 
    {
        int total = 0;
        // Add the value in each element of hourCounts to // total.
        for(int i = 0; i < hourCounts.length; i++) {
            total += hourCounts[i];
        }
        return total;
    }
    
    /**
     * Find the busiest hour.
     * @return The busiest hour.
     * 
     */
    public int busiestHour() 
    {
        //set the maxCount to 0,
        //and compare each hour against it;
        // if an hour has more counts, set it as the busiest.
        int maxCount = 0;
        int busiestHour = 0;
        for(int i = 0; i < hourCounts.length; i++)
            if(hourCounts[i] > maxCount) {
                busiestHour = i;
                maxCount = hourCounts[i];
            }
            return busiestHour;
    }
    
    /**
     * Find the quietest hour.
     * @return The quietest hour.
     */
    public int quietestHour()
    {
        //set the minCount to numberOfAccesses,
        //and compare each hour aagainst it;
        // if an hour has fewer counts, set it as the quietest.
        int minCount = numberOfAccesses();
        int quietestHour = 0;
        for(int i = 0; i < hourCounts.length; i++)
            if(hourCounts[i] < minCount) {
                quietestHour = i;
                minCount = hourCounts[i];
            }
            return quietestHour;
    }
    
    /**
     * Find the busiest two-hour block.
     * @return The busiest two-hour block.
     */
    public int busiestTwoHours()
    {
        //set the maxCount to 0,
        //and compare each two hours aagainst it;
        // if an hour-pair has more counts, set it as the busiest.
        int maxCount = 0;
        int firstOfBusiestHourPair = 0;
        for(int i = 0; i < hourCounts.length/2; i++) {
            int hourPair = hourCounts[i*2] + hourCounts[i*2+1];
            if(hourPair > maxCount) {
                firstOfBusiestHourPair = i;
            }
        }
        return firstOfBusiestHourPair;
    }
}
