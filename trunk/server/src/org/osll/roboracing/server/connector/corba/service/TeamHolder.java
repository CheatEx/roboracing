package org.osll.roboracing.server.connector.corba.service;

/**
* org/osll/roboracing/server/connector/corba/service/TeamHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from service.idl
* 12 Декабрь 2008 г. 14:15:39 MSK
*/

public final class TeamHolder implements org.omg.CORBA.portable.Streamable
{
  public org.osll.roboracing.server.connector.corba.service.Team value = null;

  public TeamHolder ()
  {
  }

  public TeamHolder (org.osll.roboracing.server.connector.corba.service.Team initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = org.osll.roboracing.server.connector.corba.service.TeamHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    org.osll.roboracing.server.connector.corba.service.TeamHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return org.osll.roboracing.server.connector.corba.service.TeamHelper.type ();
  }

}
