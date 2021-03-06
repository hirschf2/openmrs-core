/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.validator;

import org.junit.Assert;
import org.junit.Test;
import org.openmrs.Form;
import org.openmrs.test.BaseContextSensitiveTest;
import org.openmrs.test.Verifies;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.openmrs.FormField;


/**
 * Tests methods on the {@link FormValidator} class.
 */
public class FormValidatorTest extends BaseContextSensitiveTest{
	
	//ADDED TEST FOR CLASS
	/**
        * @see FormValidator#validate(Object,Errors) 	
	*/
	@Test
	@Verifies(value = "should fail validation if all fields are null", method = "validate(object,Errors)")
	public void validate_shouldFailValidationIfAllFieldsNull() throws Exception {
		Form form = new Form();
		Errors errors = new BindException(form, "form");
		new FormValidator().validate(form, errors);

		Assert.assertTrue(errors.hasFieldErrors("name"));
		Assert.assertTrue(errors.hasFieldErrors("version"));
	}

	/**
	 * @see FormValidator#validate(Object,Errors)
	 */
	@Test
	@Verifies(value = "should fail validation if name is null", method = "validate(Object,Errors)")
	public void validate_shouldFailValidationIfNameIsNull() throws Exception {
		Form form = new Form();
		form.setVersion("1.0");
		
		Errors errors = new BindException(form, "form");
		new FormValidator().validate(form, errors);
		
		Assert.assertTrue(errors.hasFieldErrors("name"));
		Assert.assertFalse(errors.hasFieldErrors("version"));
	}
	
	/**
	 * @see FormValidator#validate(Object,Errors)
	 */
	@Test
	@Verifies(value = "should fail validation if version is null", method = "validate(Object,Errors)")
	public void validate_shouldFailValidationIfVersionIsNull() throws Exception {
		Form form = new Form();
		form.setName("test");
		
		Errors errors = new BindException(form, "form");
		new FormValidator().validate(form, errors);
		
		Assert.assertFalse(errors.hasFieldErrors("name"));
		Assert.assertTrue(errors.hasFieldErrors("version"));
	}
	
	/**
	 * @see FormValidator#validate(Object,Errors)
	 */
	@Test
	@Verifies(value = "should fail validation if version does not match regex", method = "validate(Object,Errors)")
	public void validate_shouldFailValidationIfVersionDoesNotMatchRegex() throws Exception {
		Form form = new Form();
		form.setName("test");
		form.setVersion("first");
		
		Errors errors = new BindException(form, "form");
		new FormValidator().validate(form, errors);
		
		Assert.assertFalse(errors.hasFieldErrors("name"));
		Assert.assertTrue(errors.hasFieldErrors("version"));
	}
	
	/**
	 * @see FormValidator#validate(Object,Errors)
	 */
	@Test
	@Verifies(value = "should fail validation if retiredReason is null", method = "validate(Object,Errors)")
	public void validate_shouldFailValidationIfRetiredReasonIsNull() throws Exception {
		Form form = new Form();
		form.setName("test");
		form.setVersion("1.0");
		form.setRetired(true);
		
		Errors errors = new BindException(form, "form");
		new FormValidator().validate(form, errors);
		
		Assert.assertFalse(errors.hasFieldErrors("name"));
		Assert.assertFalse(errors.hasFieldErrors("version"));
		Assert.assertTrue(errors.hasFieldErrors("retireReason"));
	}
	
	/**
	 * @see FormValidator#validate(Object,Errors)
	 */
	@Test
	@Verifies(value = "should pass validation if all fields are correct", method = "validate(Object,Errors)")
	public void validate_shouldPassValidationIfAllFieldsAreCorrect() throws Exception {
		Form form = new Form();
		form.setName("test");
		form.setVersion("1.0");
		
		Errors errors = new BindException(form, "form");
		new FormValidator().validate(form, errors);
		
		Assert.assertFalse(errors.hasErrors());
	}
	
	/**
	 * @see FormValidator#validate(Object,Errors)
	 */
	@Test
	@Verifies(value = "should fail validation if retiredReason is empty", method = "validate(Object,Errors)")
	public void validate_shouldFailValidationIfRetiredReasonIsEmpty() throws Exception {
		Form form = new Form();
		form.setName("test");
		form.setVersion("1.0");
		form.setRetired(true);
		form.setRetireReason("");
		
		Errors errors = new BindException(form, "form");
		new FormValidator().validate(form, errors);
		
		Assert.assertTrue(errors.hasFieldErrors("retireReason"));
	}
	
	/**
	 * @see FormValidator#validate(Object,Errors)
	 */
	@Test
	@Verifies(value = "should pass validation if field lengths are correct", method = "validate(Object,Errors)")
	public void validate_shouldPassValidationIfFieldLengthsAreCorrect() throws Exception {
		Form form = new Form();
		form.setName("name");
		form.setVersion("1.0");
		form.setDescription("description");
		form.setRetireReason("retireReason");
		
		Errors errors = new BindException(form, "form");
		new FormValidator().validate(form, errors);
		
		Assert.assertFalse(errors.hasErrors());
	}
	
	/**
	 * @see FormValidator#validate(Object,Errors)
	 */
	@Test
	@Verifies(value = "should fail validation if field lengths are not correct", method = "validate(Object,Errors)")
	public void validate_shouldFailValidationIfFieldLengthsAreNotCorrect() throws Exception {
		Form form = new Form();
		form
		        .setName("too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text");
		form.setVersion("1111111111111111111111111111111111111111111111111111");
		form
		        .setDescription("too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text");
		form
		        .setRetireReason("too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text");
		
		Errors errors = new BindException(form, "form");
		new FormValidator().validate(form, errors);
		
		Assert.assertTrue(errors.hasFieldErrors("name"));
		Assert.assertTrue(errors.hasFieldErrors("version"));
		Assert.assertTrue(errors.hasFieldErrors("description"));
		Assert.assertTrue(errors.hasFieldErrors("retireReason"));
	}

	//ADDED TESTS FOR CLASS
	/**
	 * @see FormValidator#validate(Object,Errors)
	 */
	@Test
	@Verifies(value = "should return null", method = "validate(Object,Errors)")
	public void validate_shouldReturnNullIfGetOrderedFormFieldsEmpty() throws Exception {
		Form form = new Form();
		List<FormField> newList = new ArrayList<FormField>();
		newList = form.getOrderedFormFields();
		Assert.assertEquals(0, newList.size());
		
	}
}
