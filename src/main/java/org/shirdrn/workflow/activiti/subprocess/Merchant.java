package org.shirdrn.workflow.activiti.subprocess;

import java.io.Serializable;

/**
 * @author Ingta037
 *
 */
public class Merchant implements Serializable {  
    private static final long serialVersionUID = 1L;  
    public Merchant(String merchantId, int priority, short serviceType, short status) {  
        super();  
        this.merchantId = merchantId;  
        this.priority = priority;  
        this.serviceType = serviceType;  
        this.status = status;  
    }  
    public Merchant(String merchantId) {  
        this(merchantId, -1, (short)0, (short)0);  
    }  
    private String merchantId;  
    private int priority = -1;  
    private short serviceType = 0;  
    private short status = 0;  
    public String getMerchantId() {  
        return merchantId;  
    }  
    public void setMerchantId(String merchantId) {  
        this.merchantId = merchantId;  
    }  
    public int getPriority() {  
        return priority;  
    }  
    public void setPriority(int priority) {  
        this.priority = priority;  
    }  
    public short getServiceType() {  
        return serviceType;  
    }  
    public void setServiceType(short serviceType) {  
        this.serviceType = serviceType;  
    }  
    public short getStatus() {  
        return status;  
    }  
    public void setStatus(short status) {  
        this.status = status;  
    }  
    @Override  
    public String toString() {  
        return "Merchant[" + merchantId + "]";  
    }  
}  
