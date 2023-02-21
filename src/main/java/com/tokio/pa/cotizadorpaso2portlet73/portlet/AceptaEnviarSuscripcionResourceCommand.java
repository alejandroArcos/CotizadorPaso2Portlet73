/**
 * 
 */
package com.tokio.pa.cotizadorpaso2portlet73.portlet;

import com.google.gson.JsonObject;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.tokio.pa.cotizadorpaso2portlet73.constants.CotizadorPaso2Portlet73PortletKeys;

import java.io.PrintWriter;

import javax.portlet.PortletSession;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author jonathanfviverosmoreno
 *
 */

@Component(immediate = true, property = {
		"javax.portlet.name=" + CotizadorPaso2Portlet73PortletKeys.COTIZADORPASO2PORTLET73,
		"mvc.command.name=/cotizadores/paso2/aceptaSuscripcion", }, service = MVCResourceCommand.class)
public class AceptaEnviarSuscripcionResourceCommand extends BaseMVCResourceCommand {

	/* (non-Javadoc)
	 * @see com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand#doServeResource(javax.portlet.ResourceRequest, javax.portlet.ResourceResponse)
	 */
	@Override
	protected void doServeResource(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) throws Exception {
		// TODO Auto-generated method stub
		/************************** Validación metodo post **************************/
		if ( !resourceRequest.getMethod().equals("POST")  ){
			JsonObject requestError = new JsonObject();
			requestError.addProperty("code", 500);
			requestError.addProperty("msg", "Error en tipo de consulta");
			PrintWriter writer = resourceResponse.getWriter();
			writer.write(requestError.toString());
			return;
		}
		/************************** Validación metodo post **************************/
		
		JsonObject respuesta = new JsonObject();
		PrintWriter writer = resourceResponse.getWriter();
		try {
			String nombre = ParamUtil.getString(resourceRequest, "nombre");
			String nombre2 = ParamUtil.getString(resourceRequest, "nombre2");
			
			final PortletSession psession = resourceRequest.getPortletSession();
			psession.setAttribute(nombre, 1, PortletSession.APPLICATION_SCOPE);
			psession.setAttribute(nombre2, 1, PortletSession.APPLICATION_SCOPE);
			System.out.println(nombre);
			System.out.println(nombre2);
			respuesta.addProperty("code", 0);
			respuesta.addProperty("msg", "ok");
		} catch (Exception e) {
			// TODO: handle exception
			respuesta.addProperty("code", 2);
			respuesta.addProperty("msg", "Error al consultar su información");
		}
		
		writer.write(respuesta.toString());
	}

}
