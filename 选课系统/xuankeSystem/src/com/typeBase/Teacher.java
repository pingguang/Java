package com.typeBase;

public class Teacher
{
	private String username;
    private String password;
    private String tname;
    private String tage;
    private String tsex;
    private String tmajor;
    private String tposition;
    private String tcontact;
    public Teacher(String username, String password, String tname, String tage, String tsex, String tmajor, String tposition, String tcontact)
    {
        this.username = username;
        this.password = password;
        this.tname = tname;
        this.tage = tage;
        this.tsex = tsex;
        this.tmajor = tmajor;
        this.tposition = tposition;
        this.tcontact = tcontact;
    }

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getTname()
	{
		return tname;
	}

	public void setTname(String tname)
	{
		this.tname = tname;
	}

	public String getTage()
	{
		return tage;
	}

	public void setTage(String tage)
	{
		this.tage = tage;
	}

	public String getTsex()
	{
		return tsex;
	}

	public void setTsex(String tsex)
	{
		this.tsex = tsex;
	}

	public String getTmajor()
	{
		return tmajor;
	}

	public void setTmajor(String tmajor)
	{
		this.tmajor = tmajor;
	}

	public String getTposition()
	{
		return tposition;
	}

	public void setTposition(String tposition)
	{
		this.tposition = tposition;
	}

	public String getTcontact()
	{
		return tcontact;
	}

	public void setTcontact(String tcontact)
	{
		this.tcontact = tcontact;
	}
}
