/*******************************************************************************
 * Copyright (c) 2011 Red Hat, Inc.
 *  All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Red Hat, Inc. - initial API and implementation
 *
 * @author Bob Brodt
 ******************************************************************************/

package org.eclipse.bpmn2.modeler.runtime.jboss.jbpm5.property;

import org.eclipse.bpmn2.Gateway;
import org.eclipse.bpmn2.Task;
import org.eclipse.bpmn2.modeler.core.utils.PropertyUtil;
import org.eclipse.bpmn2.modeler.ui.editor.BPMN2Editor;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertiesComposite;
import org.eclipse.bpmn2.modeler.ui.property.PropertiesCompositeFactory;
import org.eclipse.bpmn2.modeler.ui.property.tasks.TaskPropertySection;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;

/**
 * This is an empty tab section which simply exists to hide the "Basic" tab
 * defined the editor UI plugin.
 * 
 * @author Bob Brodt
 *
 */
public class JbpmEmptyPropertySection extends TaskPropertySection {
	static {
		PropertiesCompositeFactory.register(Task.class, JbpmTaskPropertiesComposite.class);
	}

	@Override
	protected AbstractBpmn2PropertiesComposite createSectionRoot() {
		return new JbpmTaskPropertiesComposite(this);
	}

	@Override
	public boolean appliesTo(IWorkbenchPart part, ISelection selection) {
		return false;
	}
	
	@Override
	public boolean doReplaceTab(String id, IWorkbenchPart part, ISelection selection) {
		// only replace the tab if this is a Gateway.
		// Gateways do not have any "Basic" attributes to display
		BPMN2Editor editor = (BPMN2Editor)part;
		PictogramElement pe = PropertyUtil.getPictogramElementForSelection(selection);
		EObject selectionBO = PropertyUtil.getBusinessObjectForSelection(selection);
		// TODO: there may be others...
		return selectionBO instanceof Gateway;
	}
}