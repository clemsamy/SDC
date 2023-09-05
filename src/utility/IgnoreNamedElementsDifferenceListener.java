package utility;

import java.util.HashSet;
import java.util.Set;

import org.custommonkey.xmlunit.Difference;
import org.custommonkey.xmlunit.DifferenceConstants;
import org.custommonkey.xmlunit.DifferenceListener;
import org.w3c.dom.Node;

public final class IgnoreNamedElementsDifferenceListener implements DifferenceListener 
{

	private Set<String> blockList = new HashSet<String>();
	
	public IgnoreNamedElementsDifferenceListener(String... elementNames) 
	{
		for (String name : elementNames) 
		{
		    blockList.add(name);
		}
	}
	
	public int differenceFound(Difference difference) 
	{
		if (difference.getId() == DifferenceConstants.TEXT_VALUE_ID) 
		{
		    if (blockList.contains(difference.getControlNodeDetail().getNode()
		            .getParentNode().getNodeName())) 
		    {
		        return DifferenceListener.RETURN_IGNORE_DIFFERENCE_NODES_IDENTICAL;
		    }
		}
	
		return DifferenceListener.RETURN_ACCEPT_DIFFERENCE;
	}
	
	/*public void skippedComparison(Node node, Node node1) {
	
	}*/
	
	@Override
	public void skippedComparison(Node arg0, Node arg1) 
	{
		// TODO Auto-generated method stub
		
	}	
	
}