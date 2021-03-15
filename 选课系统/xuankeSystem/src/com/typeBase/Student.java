package com.typeBase;

public class Student
{
	private String username;

    private String password;

    private String sname;

    private String sage;

    private String ssex;

    private String smajor;

    private String sclass;

    private String ssno;

    private String scontact;

    public Student(String username, String password, String sname, String sage, String ssex, String smajor, String sclass, String ssno, String scontact) 
    {
        this.username = username;
        this.password = password;
        this.sname = sname;
        this.sage = sage;
        this.ssex = ssex;
        this.smajor = smajor;
        this.sclass = sclass;
        this.ssno = ssno;
        this.scontact = scontact;
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

	public String getSname()
	{
		return sname;
	}

	public void setSname(String sname)
	{
		this.sname = sname;
	}

	public String getSage()
	{
		return sage;
	}

	public void setSage(String sage)
	{
		this.sage = sage;
	}

	public String getSsex()
	{
		return ssex;
	}

	public void setSsex(String ssex)
	{
		this.ssex = ssex;
	}

	public String getSmajor()
	{
		return smajor;
	}

	public void setSmajor(String smajor)
	{
		this.smajor = smajor;
	}

	public String getSclass()
	{
		return sclass;
	}

	public void setSclass(String sclass)
	{
		this.sclass = sclass;
	}

	public String getSsno()
	{
		return ssno;
	}

	public void setSsno(String ssno)
	{
		this.ssno = ssno;
	}

	public String getScontact()
	{
		return scontact;
	}

	public void setScontact(String scontact)
	{
		this.scontact = scontact;
	}
}
