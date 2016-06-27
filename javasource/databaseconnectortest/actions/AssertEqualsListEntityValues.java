// This file was generated by Mendix Modeler.
//
// WARNING: Only the following code will be retained when actions are regenerated:
// - the import list
// - the code between BEGIN USER CODE and END USER CODE
// - the code between BEGIN EXTRA CODE and END EXTRA CODE
// Other code you write will be lost the next time you deploy the project.
// Special characters, e.g., é, ö, à, etc. are supported in comments.

package databaseconnectortest.actions;

import static java.lang.String.format;
import static java.util.stream.Collectors.joining;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.apache.commons.io.IOUtils;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.IMendixObject;
import com.mendix.systemwideinterfaces.core.IMendixObjectMember;
import com.mendix.webui.CustomJavaAction;
import unittesting.proxies.microflows.Microflows;

public class AssertEqualsListEntityValues extends CustomJavaAction<Boolean>
{
	private java.util.List<IMendixObject> Expected;
	private java.util.List<IMendixObject> Actual;

	public AssertEqualsListEntityValues(IContext context, java.util.List<IMendixObject> Expected, java.util.List<IMendixObject> Actual)
	{
		super(context);
		this.Expected = Expected;
		this.Actual = Actual;
	}

	@Override
	public Boolean executeAction() throws Exception
	{
		// BEGIN USER CODE
	  Consumer<String> assertMessage = a ->
	    Microflows.assertTrue2(getContext(), false, "The values of some entities in both lists do not match.\n" + a);
	  Optional<String> notEqualMessage = compare(Expected, Actual);
	  notEqualMessage.ifPresent(assertMessage);

    return !notEqualMessage.isPresent();
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 */
	@Override
	public String toString()
	{
		return "AssertEqualsListEntityValues";
	}

	// BEGIN EXTRA CODE
  private Optional<String> compare(List<IMendixObject> expected, List<IMendixObject> actual) {
    if (expected.size() != actual.size())
      return Optional.of(format("The list of expected items contains %s items,"
          + " the list of actual items %s", expected.size(), actual.size()));

    IntStream range = IntStream.range(0, expected.size());
    IntFunction<Optional<String>> compare = a -> compare(a + 1, expected.get(a), actual.get(a));
    String message = range.mapToObj(compare).flatMap(messagesFilter).collect(joining("," + System.lineSeparator()));

    return Optional.ofNullable(message);
  }

  private Optional<String> compare(int objectNr, IMendixObject expected, IMendixObject actual) {
    Function<IMendixObjectMember<?>, Optional<String>> compare = a -> compare(a, actual.getMember(getContext(), a.getName()));
    Stream<Optional<String>> potentialMessages = expected.getPrimitives(getContext()).stream().map(compare);
    String message = potentialMessages.flatMap(messagesFilter).collect(joining(", ", format("Row %s: ", objectNr), ""));

    return Optional.ofNullable(message);
  }

  private Optional<String> compare(IMendixObjectMember<?> expected, IMendixObjectMember<?> actual) {
    Object expectedValue = toComparableValue(expected.getValue(getContext()));
    Object actualValue = toComparableValue(actual.getValue(getContext()));
    boolean isEqual;

    if (expectedValue instanceof byte[] && actualValue instanceof byte[])
      isEqual = Arrays.equals((byte[]) expectedValue, (byte[]) actualValue);
    else
      isEqual = expectedValue == null ? actualValue == null : expectedValue.equals(actualValue);

    return isEqual ? Optional.empty() : Optional.of(format("%s (%s != %s)", expected.getName(), expectedValue, actualValue));
  }

  /**
   * Convert the value to a comparable value, like InputStream to byte array.
   */
  private Object toComparableValue(Object value) {
    if (value instanceof InputStream) try {
      return IOUtils.toByteArray((InputStream) value);
    } catch (IOException e) {
      throw new RuntimeException(e);
    } catch (NullPointerException e) {
      // this will occur in some kind of InputStream where an underlying input stream is null.
      return null;
    }

    return value;
  }

  private Function<Optional<String>, Stream<String>> messagesFilter = a -> a.map(Stream::of).orElseGet(Stream::empty);
	// END EXTRA CODE
}
