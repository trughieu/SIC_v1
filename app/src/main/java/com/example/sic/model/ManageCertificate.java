package com.example.sic.model;

import java.io.Serializable;

public class ManageCertificate implements Serializable {
    public String CNSubjectDN;
    public String CNIssuerDN;
    public String ValidTo;
    public String credentialID;
    public boolean kakChange;
    public String authMode;

    public String getAuthMode() {
        return authMode;
    }
    public String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAuthMode(String authMode) {
        this.authMode = authMode;
    }

    public boolean isKakChange() {
        return kakChange;
    }

    public void setKakChange(boolean kakChange) {
        this.kakChange = kakChange;
    }

    public String getCredentialID() {
        return credentialID;
    }

    public void setCredentialID(String credentialID) {
        this.credentialID = credentialID;
    }

    public String getCNIssuerDN() {
        return CNIssuerDN;
    }

    public void setCNIssuerDN(String CNIssuerDN) {
        this.CNIssuerDN = CNIssuerDN;
    }

    public String getValidTo() {
        return ValidTo;
    }

    public void setValidTo(String validTo) {
        this.ValidTo = validTo;
    }

    public String getCNSubjectDN() {
        return CNSubjectDN;
    }

    public void setCNSubjectDN(String CNSubjectDN) {
        this.CNSubjectDN = CNSubjectDN;
    }
}
