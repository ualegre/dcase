/*
 * Copyright 2015 The ContextModeller Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package edu.casetools.dcase.modelio.menu.io;

import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.modelio.api.module.IModule;
import org.modelio.api.module.command.DefaultModuleCommandHandler;
import org.modelio.vcore.smkernel.mapi.MObject;

import edu.casetools.dcase.extensions.io.gen.mobile.areasoner.AReasonerGenerator;
import edu.casetools.dcase.module.impl.DCaseModule;
import edu.casetools.rcase.utils.ModelioUtils;

/**
 * Implementation of the IModuleContextualCommand interface. <br>
 * The module contextual commands are displayed in the contextual menu and in
 * the specific toolbar of each module property page. <br>
 * The developer may inherit the DefaultModuleContextualCommand class which
 * contains a default standard contextual command implementation.
 *
 */
public class GenerateAndroidReasoningRules extends DefaultModuleCommandHandler {

	//private ILogService logService;
	private static final String aContextReasonerFolder = "\\aContextReasoner";
	/**
	 * Constructor.
	 */
	public GenerateAndroidReasoningRules() {
		super();
		// services logs
		//this.logService = DCaseModule.getInstance().getModuleContext().getModelioServices().getLogService();
	}

	/**
	 * @see org.modelio.api.module.commands.DefaultModuleContextualCommand#accept(java.util.List,
	 *      org.modelio.api.module.IModule)
	 */
	@Override
	public boolean accept(List<MObject> selectedElements, IModule module) {
		// Check that there is only one selected element
		return selectedElements.size() == 1;
	}

	/**
	 * @see org.modelio.api.module.commands.DefaultModuleContextualCommand#actionPerformed(java.util.List,
	 *      org.modelio.api.module.IModule)
	 */
	@Override
	public void actionPerformed(List<MObject> selectedElements, IModule module) {
	    String projectName = ModelioUtils.getInstance().getProjectName(DCaseModule.getInstance()).replaceAll("\\s", "");  
		DirectoryDialog dialog = new DirectoryDialog(Display.getCurrent().getActiveShell());
		String path = dialog.open();
		path = path.concat("\\").concat(projectName).concat(aContextReasonerFolder);
		AReasonerGenerator generator = new AReasonerGenerator();
		generator.generateTemplates(path);
		MessageDialog.openInformation(null, "Model Exported", "Android reasoner code skeleton generated at:\n" + path);
		

	}

}
