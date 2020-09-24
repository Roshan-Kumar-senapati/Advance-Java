package orq.oar.EcomApp;

public class MobilePrice
{
	public static int mobilePrice(String pnames,int qtys) 
	{
		switch (pnames)
		{
		case "vivo":
			return 20000*qtys;
		case "samsung":
			return 30000*qtys;
		case "oneplus":
			return 40000*qtys;
		case "apple":
			return 60000*qtys;
		}
		return -1;
	}

}
