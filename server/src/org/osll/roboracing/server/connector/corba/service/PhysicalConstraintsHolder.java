package org.osll.roboracing.server.connector.corba.service;

/**
* org/osll/roboracing/server/connector/corba/service/PhysicalConstraintsHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from service.idl
* 13 Декабрь 2008 г. 14:09:35 MSK
*/

public final class PhysicalConstraintsHolder implements org.omg.CORBA.portable.Streamable
{
  public org.osll.roboracing.server.connector.corba.service.PhysicalConstraints value = null;

  public PhysicalConstraintsHolder ()
  {
  }

  public PhysicalConstraintsHolder (org.osll.roboracing.server.connector.corba.service.PhysicalConstraints initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = org.osll.roboracing.server.connector.corba.service.PhysicalConstraintsHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    org.osll.roboracing.server.connector.corba.service.PhysicalConstraintsHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return org.osll.roboracing.server.connector.corba.service.PhysicalConstraintsHelper.type ();
  }

}
