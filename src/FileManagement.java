import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class FileManagement {
    ArrayList<ArrayList<Integer>> readingsGraph = new ArrayList<>();

    String reportLocation;
    String reportTime;
    String reportDate;

    // Reads data from the supplied file and populates the readingsGraph 2d ArrayList with it.
    public void ReadReadingsValue(String filename)
    {
        try
        {
            String line;
            String[] fields;

            int currentReadLine = 1;
            int currentRow = 0;
            readingsGraph.clear();

            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);

            while ((line = br.readLine()) != null)
            {
                switch (currentReadLine)
                {
                    case 1:
                        reportLocation = line;
                        break;
                    case 2:
                        reportDate = line;
                        break;
                    case 3:
                        reportTime = line;
                        break;
                }

                if (currentReadLine >= 4) {
                    fields = line.split(",");

                    readingsGraph.add(new ArrayList<>());

                    for (int i = 0; i < fields.length; i++) {
                        readingsGraph.get(currentRow).add(Integer.parseInt(fields[i]));
                    }
                    currentRow++;
                }
                currentReadLine++;
            }



        }
        catch (Exception e)
        {
            System.out.println(e);
        }


    }

    // Handles exporting the data as a Raf file.
    public void ExportRaf(String fileName, String location, String date, String time)
    {
        int seekPos = 150;

        try {
            RandomAccessFile file = new RandomAccessFile(fileName, "rw");

            file.seek(0);
            file.writeUTF(location);
            file.seek(50);
            file.writeUTF(date);
            file.seek(100);
            file.writeUTF(time);

            for (int i = 0; i < readingsGraph.size(); i++)
                for (int i2 = 0; i2 < readingsGraph.get(i).size(); i2++)
                {
                    file.seek(seekPos);
                    file.writeUTF(Integer.toString(readingsGraph.get(i).get(i2)));
                    seekPos += 50;
                }

        }
        catch (Exception e)
        {

        }
    }

    // Handles exporting the data as a Dat file.
    public void ExportDat(String fileName)
    {
        try
        {
            PrintWriter writer = new PrintWriter(new FileWriter(fileName));

            for (int i = 0; i < readingsGraph.size(); i++)
            {
                for (int i2 = 0; i2 < readingsGraph.get(i).size(); i2++)
                {
                    String readingValue = Integer.toString(readingsGraph.get(i).get(i2));

                    writer.println(i2 + "," + i + "," + readingValue);
                }
            }

            writer.close();
        }
        catch (Exception e)
        {

        }
    }

    // Handles exporting the data as a Rpt file.
    public void ExportRpt(String fileName, String[][] array)
    {
        try
        {
            PrintWriter writer = new PrintWriter(new FileWriter(fileName));

            for (int i = 0; i < array.length; i++)
            {
                int count = 0;

                String colour = array[i][0];
                String currentColour = colour;

                for (int i2 = 0; i2 < array[i].length; i2++) {
                    currentColour = array[i][i2];

                    if (colour.equalsIgnoreCase(currentColour) == false)
                    {
                        writer.print(count + "," + colour + ",");

                        colour = currentColour;

                        count = 0;
                    }

                    if (colour.equalsIgnoreCase(currentColour))
                    {
                        count++;
                    }
                }

                writer.print(count + "," + colour + ",");

                colour = currentColour;

                writer.println("");
            }
            writer.close();
        }
        catch (Exception e)
        {

        }
    }

    // Returns the size of the parent 2d ArrayList.
    public int Get2dParentSize()
    {
        return readingsGraph.size();
    }

    // Returns the size of the child 2d ArrayList.
    public int Get2dChildSize(int i)
    {
        return readingsGraph.get(i).size();
    }

    // Returns the value of the supplied index of the 2d ArrayList.
    public int Get2dArrayValue(int i1, int i2)
    {
        return readingsGraph.get(i1).get(i2);
    }

    // Returns the reportLocation from the file.
    public String GetReportLocation()
    {
        return reportLocation;
    }
    // Returns the reportTime from the file.
    public String GetReportTime()
    {
        return reportTime;
    }
    // Returns the reportDate from the file.
    public String GetReportDate()
    {
        return reportDate;
    }
}
