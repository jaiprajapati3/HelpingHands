package com.example.hetal13.afinal;

/**
 * Created by Hetal13 on 01-04-2017.
 */
public class offerPojo {
             private String spType,spName,offerDesc;
             public offerPojo(String spType, String spName, String offerDesc) {
                     this.offerDesc = offerDesc;
                     this.spName = spName;
                     this.spType = spType;
                 }
         	public String getspType() {
                     return spType;
                 }
         	public String getspName(){
             		return spName;
             	}
         	public String getofferDesc(){
             		return offerDesc;
             	}
         	public void setspType(String spType) {
                     this.spType = spType;
                 }
         	public void setspName(String spName) {
                     this.spName = spName;
                 }
         	public void setofferDesc(String offerDesc) {
                     this.offerDesc = offerDesc;
                 }
         }

