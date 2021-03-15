package com.typeBase;

public class Admin
{
	private String username;

    private String password;

    private String aname;
    
    private String acontact;
    
    public Admin(String username, String password, String aname, String acontact) 
    {
        this.username = username;
        this.password = password;
        this.aname = aname;
        this.acontact = acontact;
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

	public String getAname()
	{
		return aname;
	}

	public void setAname(String aname)
	{
		this.aname = aname;
	}

	public String getAcontact()
	{
		return acontact;
	}

	public void setAcontact(String acontact)
	{
		this.acontact = acontact;
	}
}
