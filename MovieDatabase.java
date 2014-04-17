import java.io.*;
import java.util.StringTokenizer;
import java.util.regex.Matcher;

public class MovieDatabase
{
	public static void main(String args[])
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true)
		{
			try
			{
				String input = br.readLine();
				if (input.compareTo("QUIT") == 0) break;
				calculate(input);
			}
			catch (Exception e)
			{
				System.out.println("입력이 잘못되었습니다. 오류 : " + e.toString());
			}
		}
	}

	private static void calculate(String input)
	{
		int state = 0;
		String command = "";
		String genre   = "";
		String title   = "";
		String keyword = "";
		
		// Start of input parsing using FSM.
		StringTokenizer stok = new StringTokenizer(input, "%", false);		
		while (stok.hasMoreTokens())
		{
			String s = stok.nextToken();
			if (state == 0 && s.contains("INSERT"))
			{
				command = "INSERT";
				state = 1;
				continue;
			}
			if (state == 0 && s.contains("DELETE"))
			{
				command = "DELETE";
				state = 1;
				continue;
			}

			if (state == 0 && s.contains("SEARCH"))
			{
				command = "SEARCH";
				state = 11;
				continue;
			}
			if (state == 0 && s.contains("PRINT"))
			{
				command = "PRINT";
				state = 4;
				continue;
			}
			if ( state == 1 )
			{
				genre = s;
				state = 2;
				continue;
			}
			if ( state == 2 )
			{
				state = 3;
				continue;
			}
			if ( state == 3 )
			{
				title = s;
				state = 4;
				continue;
			}
			if ( state == 4 )
			{
				break;
			}
			if ( state == 11 )
			{
				keyword = s;
				state = 4;
				continue;
			}			
		}
		// End of input parsing using FSM.
		
		if (!command.equals("")) System.out.println(command);
		if (!genre.equals(""))   System.out.println(genre);
		if (!title.equals(""))   System.out.println(title);
		if (!keyword.equals("")) System.out.println(keyword);
	}
}
