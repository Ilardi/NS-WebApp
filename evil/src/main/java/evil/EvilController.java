package evil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EvilController {

	@GetMapping("/Xss-Reflected-Vuln")
	public String XssReflectedVuln() {
		return "XssReflectedVuln";
	}
	
	@GetMapping("/Xss-Reflected-Safe")
	public String XssReflectedSafe() {
		return "XssReflectedSafe";
	}
	
	@GetMapping("/Csrf-Vuln")
	public String CsrfVuln() {
		return "CsrfVuln";
	}
	
	@GetMapping("/Csrf-Safe")
	public String CsrfSafe() {
		return "CsrfSafe";
	}
	
	@GetMapping("/Xss-Dom-Vuln")
	public String XssDomVuln() {
		return "XssDomVuln";
	}
	
	@GetMapping("/Xss-Dom-Safe")
	public String XssDomSafe() {
		return "XssDomSafe";
	}
	
}
