package passwordstrengthmeter.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * A password strength meter. Contains a PasswordTextBox, a HTML-object textually representing the strength
 * and a Label visually representing the strength of the password.
 * @author magpe097
 * @version 1.0
 */
public class GWT_PasswordStrengthMeter implements EntryPoint, KeyUpHandler {

	PasswordTextBox ptb;
	
	HTML strengthText;
	Label strengthMeter;
	
	int strengthLevel = 0;
	int unitLength = 20;

	String strengthString = getString(strengthLevel);
	String colorString = getColorString(strengthLevel);

	/**
	 * Gets called on load. Creates, setups, and adds the GUI to the rootpanel.
	 */
	public void onModuleLoad() {
		//Setup PasswordTextBox and add listeners.
		ptb = new PasswordTextBox();
		ptb.addKeyUpHandler(this);

		//Instantiate strengthText and strengthMeter to the correct text and color
		strengthText = new HTML("<b>Strength: </b>  <font color=" + colorString
				+ ">" + strengthString + "</font>");

		strengthMeter = new Label("`");
		strengthMeter.getElement().getStyle().setProperty("backgroundColor", colorString);
		strengthMeter.setSize(unitLength * (strengthLevel + 1) + "px", 20 + "px");

		//Add the strengthText and strengthMeter to a vertical panel.
		VerticalPanel vp = new VerticalPanel();
		vp.add(strengthText);
		vp.add(strengthMeter);
		
		//Add PasswordTextBox and VerticalPanel to the rootpanel.
		RootPanel.get().add(ptb);
		RootPanel.get().add(vp);
	}

	/**
	 * Returns the String associated with the given strength level.
	 * 
	 * 0 = Too short.
	 * 1 = Weak.
	 * 2 = Fair.
	 * 3 = Good.
	 * 4 = Strong.
	 * 5 = Very strong.
	 * >5 = Super strong.
	 * 
	 * @param strength Strength level to get String from.
	 * @return String correspondent with the strength level.
	 */
	public String getString(int strength) {
		switch (strength) {
		case 0:
			return "Too short";
		case 1:
			return "Weak";
		case 2:
			return "Fair";
		case 3:
			return "Good";
		case 4:
			return "Strong";
		case 5:
			return "Very strong";
		default:
			return "Super strong";
		}
	}

	/**
	 * Returns a string of a HTML-color associated with the given strength level.
	 * 
	 * 0 = Gray
	 * 1 = Red.
	 * 2 = Orange.
	 * 3 = GoldenRod.
	 * 4 = Blue.
	 * 5 = Green.
	 * >5 = Purple.
	 * 
	 * @param strength Strength level to get color-string from.
	 * @return String of HTML-color correspondent with the strength level.
	 */
	public String getColorString(int strength) {
		switch (strength) {
		case 0:
			return "gray";
		case 1:
			return "red";
		case 2:
			return "orange";
		case 3:
			return "GoldenRod";
		case 4:
			return "blue";
		case 5:
			return "green";
		default:
			return "purple";
		}
	}

	/**
	 * Calculates the strength of the password entered in the PasswordTextBox.
	 * Updates the strengthString and colorString variables, and then
	 * sets them to strengthText and strengthMeter.
	 */
	public void update() {
		//calculate strength
		strengthLevel = PasswordStrengthCalculator.getStrength(ptb.getText());

		//update variables
		strengthString = getString(strengthLevel);
		colorString = getColorString(strengthLevel);

		//update GUI-components with updated variables.
		strengthText.setHTML("<b>Strength:</b>  <font color=" + colorString
				+ ">" + strengthString + "</font>");
		
		strengthMeter.getElement().getStyle().setProperty("backgroundColor", colorString);
		strengthMeter.setSize(unitLength * (strengthLevel + 1) + "px", 20 + "px");
	}

	/**
	 * Gets called when user types. Calls update() which updates GUI-components with new strength.
	 */
	public void onKeyUp(KeyUpEvent event) {
		update();
	}

}
