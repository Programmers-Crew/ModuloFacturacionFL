package org.moduloFacturacion.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexion {
    
    private Connection conexion;
    public static Conexion instancia;
    
    public Conexion(){
        try{
          
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            //192.168.1.2
                conexion = DriverManager.getConnection("jdbc:mysql://192.168.1.2:3306/ProgrammersBilling?useSSL=False", "ProgrammersL1" , "ProgrammersLicenciaUno");
                    }catch(ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e){
                            e.printStackTrace();
                    }
}


    public static Conexion getIntance(){
        if(instancia == null){
               instancia = new Conexion();   
        }
        return instancia;
    }

    public Connection getConexion(){
        return conexion;   
    }

    public void setConexion(Connection conexion){
        this.conexion = conexion;
    }
}