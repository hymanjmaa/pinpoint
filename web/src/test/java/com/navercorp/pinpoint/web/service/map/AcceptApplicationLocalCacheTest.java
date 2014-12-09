package com.navercorp.pinpoint.web.service.map;

import com.navercorp.pinpoint.common.ServiceType;
import com.navercorp.pinpoint.web.service.map.AcceptApplication;
import com.navercorp.pinpoint.web.service.map.AcceptApplicationLocalCache;
import com.navercorp.pinpoint.web.service.map.RpcApplication;
import com.navercorp.pinpoint.web.vo.Application;

import junit.framework.Assert;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;


public class AcceptApplicationLocalCacheTest {


    @Test
    public void testFind() throws Exception {
        AcceptApplicationLocalCache cache = new AcceptApplicationLocalCache();

        Application tomcat = new Application("Tomcat", ServiceType.TOMCAT);
        RpcApplication rpc = new RpcApplication("localhost:8080", tomcat);
        // TOMCAT 에서 localhost:8080을 rpc로 호출시 accept한 Application을 찾는다.

        Set<AcceptApplication> findSet =  createAcceptApplication();

        cache.put(rpc, findSet);

        // 찾음.
        Set<AcceptApplication> acceptApplications = cache.get(rpc);
        Assert.assertEquals(acceptApplications.size(), 1);
        Assert.assertEquals(acceptApplications.iterator().next(), localhost);

        // 못찾음.
        Set<AcceptApplication> unknown = cache.get(new RpcApplication("unknown:8080", tomcat));
        Assert.assertTrue(unknown.isEmpty());
        Assert.assertFalse(unknown.iterator().hasNext());

    }

    AcceptApplication localhost = new AcceptApplication("localhost:8080", new Application("LOCALHOST", ServiceType.BLOC));

    private Set<AcceptApplication> createAcceptApplication() {
        AcceptApplication naver = new AcceptApplication("www.naver.com", new Application("Naver", ServiceType.BLOC));
        AcceptApplication daum = new AcceptApplication("www.daum.com", new Application("Daum", ServiceType.BLOC));
        AcceptApplication nate = new AcceptApplication("www.nate.com", new Application("Nate", ServiceType.BLOC));




        Set<AcceptApplication> result = new HashSet<AcceptApplication>();
        result.add(naver);
        result.add(daum);
        result.add(nate);
        result.add(localhost);

        return result;
    }
}