package org.osll.roboracing.server.connector.corba.service;


/**
* org/osll/roboracing/server/connector/corba/service/ServerConnectionPOATie.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from service.idl
* 12 Декабрь 2008 г. 14:15:39 MSK
*/

public class ServerConnectionPOATie extends ServerConnectionPOA
{

  // Constructors

  public ServerConnectionPOATie ( org.osll.roboracing.server.connector.corba.service.ServerConnectionOperations delegate ) {
      this._impl = delegate;
  }
  public ServerConnectionPOATie ( org.osll.roboracing.server.connector.corba.service.ServerConnectionOperations delegate , org.omg.PortableServer.POA poa ) {
      this._impl = delegate;
      this._poa      = poa;
  }
  public org.osll.roboracing.server.connector.corba.service.ServerConnectionOperations _delegate() {
      return this._impl;
  }
  public void _delegate (org.osll.roboracing.server.connector.corba.service.ServerConnectionOperations delegate ) {
      this._impl = delegate;
  }
  public org.omg.PortableServer.POA _default_POA() {
      if(_poa != null) {
          return _poa;
      }
      else {
          return super._default_POA();
      }
  }
  public String connect (String name, org.osll.roboracing.server.connector.corba.service.Team team)
  {
    return _impl.connect(name, team);
  } // connect

  private org.osll.roboracing.server.connector.corba.service.ServerConnectionOperations _impl;
  private org.omg.PortableServer.POA _poa;

} // class ServerConnectionPOATie