package com.hcl.demo.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;
import java.beans.PropertyDescriptor;
import java.util.*;
import java.util.function.Function;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hcl.demo.helper.IAuthenticationFacade;

/**
 * The Class AuditTrailHelper.
 */
 @Service
public class AuditTrailHelper {

	private final Logger log = LogManager.getLogger(AuditTrailHelper.class);
	
	@Value("${audittrail.createdby}") 
	public String createdBy;
	@Value("${audittrail.createdon}") 
	public String createdOn;
	@Value("${audittrail.updatedby}") 
	public String updatedBy;
	@Value("${audittrail.updatedon}") 
	public String updatedOn;
	
	String user = "";

    @Autowired
    private IAuthenticationFacade authenticationFacade;

	public void createAuditTrail(Object obj) {
	
	log.info("Start createAuditTrail for Class: " + obj.getClass());
	if(authenticationFacade!=null && authenticationFacade.getAuthentication()!=null && authenticationFacade.getAuthentication().getName()!=null) {
	user = authenticationFacade.getAuthentication().getName();
	}
	log.info("User: " + user);
		
		Date date = new Date();
		setAuditTrail(obj, new Function<Object, Object>() {
			@Override
			public Object apply(Object t) {
				log.info("Start createAuditTrail.apply for Class: " + t.getClass().getSimpleName());
				try {
	 			    BeanUtils.getPropertyDescriptor(t.getClass(), createdOn).getWriteMethod().invoke(t, date);
				    BeanUtils.getPropertyDescriptor(t.getClass(), createdBy).getWriteMethod().invoke(t, user);
				} catch (Exception e) {
					log.error(e);
				}
				log.info("End createAuditTrail.apply for Class: " + t.getClass().getSimpleName());
				return t;
			}
		});
		log.debug("End createAuditTrail for Class: " + obj.getClass());
	}

	public void updateAuditTrail(Object obj) {
	
	log.info("Start updateAuditTrail for Class: " + obj.getClass());
	if(authenticationFacade!=null && authenticationFacade.getAuthentication()!=null && authenticationFacade.getAuthentication().getName()!=null) {
	user = authenticationFacade.getAuthentication().getName();
	}
	log.info("User: " + user);

		Date date = new Date();
		setAuditTrail(obj, new Function<Object, Object>() {
			@Override
			public Object apply(Object t) {
				log.info("Start updateAuditTrail.apply for Class: " + t.getClass().getSimpleName());
				try {
					BeanUtils.getPropertyDescriptor(t.getClass(), updatedOn).getWriteMethod().invoke(t, date);
					BeanUtils.getPropertyDescriptor(t.getClass(), updatedBy).getWriteMethod().invoke(t, user);
				} catch (Exception e) {
					log.error(e);
				}
				log.info("End updateAuditTrail.apply for Class: " + t.getClass().getSimpleName());
				return t;
			}
		});
		log.info("End updateAuditTrail for Class: " + obj.getClass());
	}

	private void setAuditTrail(Object obj, Function<Object, Object> fn) {
		log.debug("Start setAuditTrail for Class: " + obj.getClass());

		try {

			fn.apply(obj);

			PropertyDescriptor[] prpDescs = BeanUtils.getPropertyDescriptors(obj.getClass());
			for (PropertyDescriptor prpDesc : prpDescs) {

				if (!BeanUtils.isSimpleProperty(prpDesc.getPropertyType())) {
					
					if (obj.getClass().getDeclaredField(prpDesc.getName()).getAnnotation(JsonBackReference.class) == null) {
						Object value = prpDesc.getReadMethod().invoke(obj, (Object[]) null);
						if (value != null) {
							if (prpDesc.getPropertyType().isAssignableFrom(Set.class)) {
								Set<?> elements = (Set<?>) value;
								for (Object elem : elements) {
									setAuditTrail(elem, fn);
								}

							} else {
								setAuditTrail(value, fn);
							}
						}
						
					}

				}
			}
		} catch (Exception e) {
			log.error(e);
		}

		log.debug("End setAuditTrail for Class: " + obj.getClass().getSimpleName());
	}

	
}