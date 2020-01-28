// This file was generated by Mendix Studio Pro.
//
// WARNING: Only the following code will be retained when actions are regenerated:
// - the import list
// - the code between BEGIN USER CODE and END USER CODE
// - the code between BEGIN EXTRA CODE and END EXTRA CODE
// Other code you write will be lost the next time you deploy the project.
// Special characters, e.g., é, ö, à, etc. are supported in comments.

package objecthandling.actions;

import java.util.ArrayList;
import java.util.List;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.webui.CustomJavaAction;
import com.mendix.systemwideinterfaces.core.IMendixObject;

public class createObjectListFromObject extends CustomJavaAction<java.util.List<IMendixObject>>
{
	private IMendixObject inputObject;

	public createObjectListFromObject(IContext context, IMendixObject inputObject)
	{
		super(context);
		this.inputObject = inputObject;
	}

	@java.lang.Override
	public java.util.List<IMendixObject> executeAction() throws Exception
	{
		// BEGIN USER CODE
		List<IMendixObject> objectList = new ArrayList<IMendixObject>();
		objectList.add(this.inputObject);
		
		return objectList;
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 */
	@java.lang.Override
	public java.lang.String toString()
	{
		return "createObjectListFromObject";
	}

	// BEGIN EXTRA CODE
	// END EXTRA CODE
}
