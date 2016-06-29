// This file was generated by Mendix Modeler.
//
// WARNING: Only the following code will be retained when actions are regenerated:
// - the import list
// - the code between BEGIN USER CODE and END USER CODE
// - the code between BEGIN EXTRA CODE and END EXTRA CODE
// Other code you write will be lost the next time you deploy the project.
// Special characters, e.g., é, ö, à, etc. are supported in comments.

package databaseconnectortest.actions;

import com.mendix.core.objectmanagement.member.MendixBinary;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.IMendixObject;
import com.mendix.webui.CustomJavaAction;

public class SetBinaryTextValue extends CustomJavaAction<Boolean>
{
	private IMendixObject instance;
	private String member;
	private String value;

	public SetBinaryTextValue(IContext context, IMendixObject instance, String member, String value)
	{
		super(context);
		this.instance = instance;
		this.member = member;
		this.value = value;
	}

	@Override
	public Boolean executeAction() throws Exception
	{
		// BEGIN USER CODE
	  MendixBinary binaryMember = (MendixBinary) instance.getMember(getContext(), member);
	  binaryMember.setValue(getContext(), value.getBytes());

	  return true;
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 */
	@Override
	public String toString()
	{
		return "SetBinaryTextValue";
	}

	// BEGIN EXTRA CODE
	// END EXTRA CODE
}
