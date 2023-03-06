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
    // Where to calculate the daily access counts.
    private int[] dailyCounts;
    // Where to calculate the weekly access counts.
    private int[] weeklyCounts;
    // Where to calculate the access outcome counts.
    private int[] accessOutcomeCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer(String name)
    { 
        // Create the array object to hold the hourly, daily and weekly
        // access counts.
        hourCounts = new int[24];
        dailyCounts = new int[366];
        weeklyCounts = new int[7];
        accessOutcomeCounts = new int[3];
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
    
    /**
     * Analyze the daily access data from the log file.
     */
    public void analyzeDailyData() 
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int day = entry.getDay();
            dailyCounts[day]++;
        }
    }
    
    /**
     * Analyze the 7-day access patterns
     * (starting from January 1st)
     */
    public int[] analyzeWeeklyPatterns()
    {
        //To get at the 7-day pattern,
        //we need to loop through each week,
        //and sum counts for each day of the week
        for(int i = 0; i < 52; i++) {
            //Looping through each week
            for(int j = 0; j < 7; j++) {
                //Loooping through each day
                weeklyCounts[j] += dailyCounts[i*7+j];
            }
        }
        //additionally we need to manually add the last
        //two days of the year (because 7 * 52 = 364 + 2 = 366
        weeklyCounts[0] += dailyCounts[364];
        weeklyCounts[1] += dailyCounts[365];
        return weeklyCounts;
    }
    
    /**
     * Find the busiest day in a 7-day cycle.
     * @return The busiest day.
     */
    public int busiestDay()
    {
        //set the maxCount to 0,
        //and compare each day against it;
        // if a day has more counts, set it as the busiest
        int maxCount = 0;
        int busiestDay = 0;
        for(int i = 0; i < weeklyCounts.length; i++)
            if(weeklyCounts[i] > maxCount)
                busiestDay = i;
        return busiestDay;
    }
    
    /**
     * Find the quietest day in a 7-day cycle.
     * @return The quietest day.
     */
    public int quietestDay()
    {
        //set the minCount to numberOfAccesses,
        //and compare each day against it;
        // if a day has fewer counts, set it as the quietest
        int minCount = numberOfAccesses();
        int quietestDay = 0;
        for(int i = 0; i < weeklyCounts.length; i++)
            if(hourCounts[i] < minCount){
                quietestDay = i;
                minCount = weeklyCounts[i];
            }
        return quietestDay;
    }
    
    /**
     * Print the access outcome counts.
     */
    public void analyzeAccessOutcomes()
    {
        reader.reset();
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int accessOutcome = entry.getAccessoutcome();
            if(accessOutcome == 200){
                accessOutcomeCounts[0]++;
            } else if (accessOutcome == 403) {
                accessOutcomeCounts[1]++;
            } else {
                accessOutcomeCounts[2]++;
            }
        }
        System.out.println("There were " +
            accessOutcomeCounts[0] +
            " successful access attempts, " +
            accessOutcomeCounts[1] +
            " attempts to open a secure document, and " +
            accessOutcomeCounts[2] +
            " attempts to open a file that does not exist.");
    }
}
