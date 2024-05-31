package org.example.beans;

import org.example.mbean.Area;
import org.example.mbean.AreaMBean;
import org.example.mbean.Hits;
import org.example.mbean.HitsMBean;

import javax.annotation.PostConstruct;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class InfoBean {

    private HitsMBean hits;
    private AreaMBean area;

    @PostConstruct
    private void init(){

        try {
            MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
            ObjectName hitsName = new ObjectName("org.example.beans:type=Hits");
            ObjectName areaName = new ObjectName("org.example.beans:type=Area");
            hits = new Hits();
            area = new Area();
            mBeanServer.registerMBean(hits, hitsName);
            mBeanServer.registerMBean(area, areaName);
        } catch (Exception ignored){
        }

    }

    public void registerShot(double r, boolean status){
        hits.registerShot(status);
        area.registerShot(r);
    }
}
