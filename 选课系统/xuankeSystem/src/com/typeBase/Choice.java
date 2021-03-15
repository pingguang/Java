package com.typeBase;

public class Choice
{
	private String cname;

    private String sname;

    private String smajor;

    private String sclass;

    public Choice(String cname, String sname, String smajor, String sclass) 
    {
        this.cname = cname;
        this.sname = sname;
        this.smajor = smajor;
        this.sclass = sclass;
    }

	public String getCname()
	{
		return cname;
	}

	public void setCname(String cname)
	{
		this.cname = cname;
	}

	public String getSname()
	{
		return sname;
	}

	public void setSname(String sname)
	{
		this.sname = sname;
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
}
