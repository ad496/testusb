package com.company.data.session;

import java.io.Serializable;
import java.util.List;

public class SessionPackageList implements Serializable {
    private static final long serialVersionUID = 44L;

    private SessionPackage[] packages;

    public SessionPackageList() {
    }

    public SessionPackageList(SessionPackage sessionPackage) {
        SessionPackage[] packages = new SessionPackage[1];
        packages[0] = sessionPackage;
        this.packages = packages;
    }
    public SessionPackageList(List<SessionPackage> packages) {
        this.packages = packages.toArray(new SessionPackage[0]);
    }

    public SessionPackageList(SessionPackage[] packages) {
        this.packages = packages;
    }

    public SessionPackage[] getPackages() {
        return packages;
    }

    public void setPackages(SessionPackage[] packages) {
        this.packages = packages;
    }

    public SessionPackage get(int i) {
        return packages == null ? null : packages[i];
    }
}
