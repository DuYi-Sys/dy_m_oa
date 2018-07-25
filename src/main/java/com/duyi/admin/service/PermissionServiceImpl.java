/**
 * 
 */
package com.duyi.admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import com.duyi.admin.domain.AdminOperationInfo;
import com.duyi.admin.domain.AdminRoleInfo;
import com.duyi.commons.AbstractSmartLifecycle;
import com.duyi.commons.log.Trace;

/**
 * @author wangyan
 *
 */
@Service
@DependsOn("adminRoleService")
public class PermissionServiceImpl extends AbstractSmartLifecycle implements IPermissionService {
	private static Trace log=Trace.register(PermissionServiceImpl.class);

	private Thread thread;
	@Autowired
	private IAdminRoleService roleService;
	@Autowired
	private IAdminOperationService operationService;
	
	private Map<String, List<AdminOperationInfo>> operationMap=new HashMap<>();

	/* (non-Javadoc)
	 * @see com.duyi.admin.service.IPermissionService#hasPermission(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean hasPermission(String path, String method,String[] roleNames) {
		for(String roleName:roleNames) {
			List<AdminOperationInfo> operationInfos=operationMap.get(roleName);
			if(operationInfos==null || operationInfos.size()==0) {
				return false;
			}
			for (AdminOperationInfo operationInfo : operationInfos) {
				log.info("operation:" + operationInfo);
				if (path.startsWith(operationInfo.getPath())) {
					if ("*".equals(operationInfo.getOperation())) {
						return true;
					}else if("GET".equalsIgnoreCase(method) && "READ".equalsIgnoreCase(operationInfo.getOperation())) {
						return true;
					}else if(
							("POST".equalsIgnoreCase(method)||"PUT".equalsIgnoreCase(method)|| "PATCH".equalsIgnoreCase(method)) 
							&& "WRITE".equalsIgnoreCase(operationInfo.getOperation())) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.duyi.commons.AbstractSmartLifecycle#doStart()
	 */
	@Override
	protected void doStart() {
		thread=new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					List<AdminRoleInfo> roles=roleService.findAllRoles();
					for(AdminRoleInfo role :roles) {
						operationMap.put(role.getName(), role.getOperations());
					}
					try {
						Thread.sleep(5*60*1000L);
					} catch (InterruptedException e) {
					
					}
				}
			}
		});
		
		
		log.info("=======================operation service start=====");
		
	}

	/* (non-Javadoc)
	 * @see com.duyi.commons.AbstractSmartLifecycle#doStop()
	 */
	@Override
	protected void doStop() {
		thread.interrupt();
		thread=null;
		operationMap.clear();
	}
}
