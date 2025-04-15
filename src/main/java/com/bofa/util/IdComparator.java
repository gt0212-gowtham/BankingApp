package com.bofa.util;

import com.bofa.model.ServiceModel;

import java.util.Comparator;


public class IdComparator implements Comparator<ServiceModel> {
    @Override
    public int compare(ServiceModel s1, ServiceModel s2) {
        return Long.compare(s1.getServiceId(), s2.getServiceId());
    }
}
