/*
 * Copyright 2015 @author Unai Alegre 
 * 
 * This file is part of R-CASE (Requirements for Context-Aware Systems Engineering), a module 
 * of Modelio that aids the requirements elicitation phase of a Context-Aware System (C-AS). 
 * 
 * R-CASE is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * R-CASE is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Modelio. If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package edu.casetools.dcase.modelio.properties.pages.acqmod;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.runtime.AssertionFailedException;
import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.ModelElement;

import edu.casetools.dcase.module.api.DCaseProperties;
import edu.casetools.dcase.module.i18n.I18nMessageService;
import edu.casetools.dcase.module.impl.DCaseModule;
import edu.casetools.dcase.module.impl.DCasePeerModule;
import edu.casetools.rcase.modelio.properties.IPropertyContent;
import edu.casetools.rcase.utils.PropertiesUtils;

public class MobileSensorPropertyPage implements IPropertyContent {

    private static final Logger LOGGER = Logger.getLogger(MobileSensorPropertyPage.class.getName());

    // TODO Reduce the complexity of the switch case
    @Override
    public void changeProperty(ModelElement element, int row, String value) {
	try {
	    switch (row-1) {
	    case 1:
		PropertiesUtils.getInstance().findAndAddValue(DCaseModule.getInstance(), DCasePeerModule.MODULE_NAME,
			DCaseProperties.PROPERTY_MOBILE_SENSOR_DRIVER, value, element);
		break;	    
	    case 2:
		PropertiesUtils.getInstance().findAndAddValue(DCaseModule.getInstance(), DCasePeerModule.MODULE_NAME,
			DCaseProperties.PROPERTY_MOBILE_SENSOR_LIBRARY, value, element);
		break;
	    case 3:
		PropertiesUtils.getInstance().findAndAddValue(DCaseModule.getInstance(), DCasePeerModule.MODULE_NAME,
			DCaseProperties.PROPERTY_MOBILE_SENSOR_FREQUENCY, value, element);
		break;
	    case 4:
			element.putTagValue(DCasePeerModule.MODULE_NAME, DCaseProperties.PROPERTY_MOBILE_SENSOR_ONTOLOGY,
					value);
		break;
	    case 5:
		element.putTagValue(DCasePeerModule.MODULE_NAME, DCaseProperties.PROPERTY_MOBILE_SENSOR_DATA,
			value);
		break;
	    default:
		break;
	    }
	} catch (ExtensionNotFoundException | AssertionFailedException e) {
	    LOGGER.log(Level.SEVERE, e.getMessage(), e);
	}

    }

    @Override
    public void update(ModelElement element, IModulePropertyTable table) {
	String property;
	
	property = element.getTagValue(DCasePeerModule.MODULE_NAME, DCaseProperties.PROPERTY_MOBILE_SENSOR_DRIVER);
	table.addProperty(I18nMessageService.getString("Ui.MobileSensorPropertyPage.Property.Driver"),
		property,
		new String[] {
			I18nMessageService.getString("Ui.MobileSensorPropertyPage.Property.Driver.NewDriver"),
			I18nMessageService.getString("Ui.MobileSensorPropertyPage.Property.Driver.Weather"),
			I18nMessageService.getString("Ui.MobileSensorPropertyPage.Property.Driver.Battery"),					
			I18nMessageService.getString("Ui.MobileSensorPropertyPage.Property.Driver.Compass"),
			I18nMessageService.getString("Ui.MobileSensorPropertyPage.Property.Driver.Location"),
			I18nMessageService.getString("Ui.MobileSensorPropertyPage.Property.Driver.DistanceTravelled"),					
			I18nMessageService.getString("Ui.MobileSensorPropertyPage.Property.Driver.ExternalStorageSpace"),
			I18nMessageService.getString("Ui.MobileSensorPropertyPage.Property.Driver.GPSIndoor"),
			I18nMessageService.getString("Ui.MobileSensorPropertyPage.Property.Driver.Light"),					
			I18nMessageService.getString("Ui.MobileSensorPropertyPage.Property.Driver.PluggedIn"),
			I18nMessageService.getString("Ui.MobileSensorPropertyPage.Property.Driver.Pressure"),
			I18nMessageService.getString("Ui.MobileSensorPropertyPage.Property.Driver.RelativeHumidity"),					
			I18nMessageService.getString("Ui.MobileSensorPropertyPage.Property.Driver.StepCounter"),
			I18nMessageService.getString("Ui.MobileSensorPropertyPage.Property.Driver.Telephony"),
			I18nMessageService.getString("Ui.MobileSensorPropertyPage.Property.Driver.Temperature"),						
			I18nMessageService.getString("Ui.MobileSensorPropertyPage.Property.Driver.Wifi"),
			I18nMessageService.getString("Ui.MobileSensorPropertyPage.Property.Driver.HeartRate"),
			I18nMessageService.getString("Ui.MobileSensorPropertyPage.Property.Driver.Mood") });
	
		if(property!=null)
			table = getValue(property, element, table);

		table = getFrequency(element, table);
		
		
		property = PropertiesUtils.getInstance().getTaggedValue(DCaseProperties.PROPERTY_MOBILE_SENSOR_ONTOLOGY, element);
		table.addProperty(I18nMessageService.getString("Ui.MobileSensorPropertyPage.Property.Ontology"), property);

	
		property = PropertiesUtils.getInstance().getTaggedValue(DCaseProperties.PROPERTY_MOBILE_SENSOR_DATA, element);
		table.addProperty(I18nMessageService.getString("Ui.MobileSensorPropertyPage.Property.Data"), property);

    }

	private IModulePropertyTable getValue(String property, ModelElement element, IModulePropertyTable table) {
		if(property.equals(I18nMessageService.getString("Ui.MobileSensorPropertyPage.Property.Driver.NewDriver"))){
			property = element.getTagValue(DCasePeerModule.MODULE_NAME, DCaseProperties.PROPERTY_MOBILE_SENSOR_LIBRARY);
			table.addProperty(I18nMessageService.getString("Ui.MobileSensorPropertyPage.Property.Library"),
				property,
				new String[] {
					I18nMessageService.getString("Ui.MobileSensorPropertyPage.Property.Library.Sensor"),
					I18nMessageService.getString("Ui.MobileSensorPropertyPage.Property.Library.Location"),
					I18nMessageService.getString("Ui.MobileSensorPropertyPage.Property.Library.Broadcast"),			
					I18nMessageService.getString("Ui.MobileSensorPropertyPage.Property.Library.Bluetooth") });
		}else{
			table.addConsultProperty(I18nMessageService.getString("Ui.MobileSensorPropertyPage.Property.Library"),"");
		}
		
		return table;
	}

	private IModulePropertyTable getFrequency(ModelElement element, IModulePropertyTable table) {
		String responsibility =  element.getTagValue(DCasePeerModule.MODULE_NAME, DCaseProperties.PROPERTY_SENSOR_RESPONSIBILITY);
		if (responsibility == null) responsibility = "";
		if(responsibility.equals(I18nMessageService.getString("Ui.SensorPropertyPage.Property.TagResponsibility.Pull"))){
			String string = PropertiesUtils.getInstance().getTaggedValue(DCaseProperties.PROPERTY_MOBILE_SENSOR_FREQUENCY, element);
			if(string == null) string = "";
			table.addProperty(I18nMessageService.getString("Ui.MobileSensorPropertyPage.Property.Frequency"), string);
		}else{
		table.addConsultProperty(I18nMessageService.getString("Ui.MobileSensorPropertyPage.Property.Frequency"), "");
		}
		return table;
	}

}
