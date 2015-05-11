/**
 * 
 */
package co.edu.unal.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

/**
 * @author Andrés Leonardo Rojas Duarte - alrojasd@unal.edu.co
 *
 */
@SuppressWarnings("restriction")
@WebService 
@SOAPBinding(style = Style.DOCUMENT, use=Use.LITERAL)
public interface PersistTestWS {
	@WebMethod
	void doTest();
}
