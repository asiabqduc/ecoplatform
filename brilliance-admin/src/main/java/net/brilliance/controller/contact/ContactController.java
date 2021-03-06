package net.brilliance.controller.contact;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.brilliance.common.CommonUtility;
import net.brilliance.controller.base.BaseController;
import net.brilliance.controller.controller.constants.ControllerConstants;
import net.brilliance.domain.entity.crx.contact.Contact;
import net.brilliance.framework.model.SearchParameter;
import net.brilliance.service.api.contact.ContactService;

@Controller
@RequestMapping(ControllerConstants.REQUEST_URI_CONTACT)
public class ContactController extends BaseController {
	private static final String PAGE_CONTEXT = ControllerConstants.CONTEXT_WEB_PAGES + "contact/";

	private static final String PAGE_CONTEXT_BROWSE = PAGE_CONTEXT + "contactBrowse";
	private static final String PAGE_CONTEXT_SHOW = PAGE_CONTEXT + "contactShow";
	private static final String PAGE_CONTEXT_EDIT = PAGE_CONTEXT + "contactEdit";

	@Inject
	private ContactService businessServiceManager;

	@RequestMapping(path="/", method=RequestMethod.GET)
	public String goHome(){
		return PAGE_CONTEXT_BROWSE;
	}

	@RequestMapping(path="/search", method=RequestMethod.POST)
	@ResponseBody
	public String onSearch(@RequestBody(required = false) SearchParameter searchParams, Model model, Pageable pageable){
		if (CommonUtility.isEmpty(searchParams.getPageable())) {
			searchParams.setPageable(pageable);
		}
		Page<Contact> results = businessServiceManager.getObjects(searchParams);
		Gson gson = new GsonBuilder().serializeNulls().create();
		String jsonBizObjects = gson.toJson(results.getContent());
		return jsonBizObjects;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String onShow(@PathVariable("id") Long id, Model model) {
		logger.info("Fetch employee object with id: " + id);

		Contact fetchedObject = businessServiceManager.getObject(id);
		model.addAttribute(ControllerConstants.FETCHED_OBJECT, fetchedObject);
		
		return PAGE_CONTEXT_SHOW;
	}

	/**
	 * Create a new employee object and place in Model attribute.
	 */
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String showCreateForm(Model model) {
		model.addAttribute(net.brilliance.common.CommonConstants.FETCHED_OBJECT, new Contact());
		return PAGE_CONTEXT_EDIT;
	}

	/**
	 * Retrieve the book with the specified id for the update form.
	 */
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute(net.brilliance.common.CommonConstants.FETCHED_OBJECT, businessServiceManager.getObject(id));
		return PAGE_CONTEXT_EDIT;
	}

	@Override
	protected String performSearch(SearchParameter params) {
		// TODO Auto-generated method stub
		return null;
	}
}
