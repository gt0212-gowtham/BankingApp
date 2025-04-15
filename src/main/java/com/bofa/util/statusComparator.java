package com.bofa.util;

import com.bofa.model.ServiceModel;

import java.util.Comparator;

public class statusComparator implements Comparator<ServiceModel> {
    @Override
    public int compare(ServiceModel s1, ServiceModel s2) {
        return Boolean.compare(s1.isStatus(), s2.isStatus());
    }
}
