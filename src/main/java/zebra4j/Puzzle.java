/*-
 * #%L
 * zebra4j
 * %%
 * Copyright (C) 2020 Marin Nozhchev
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * #L%
 */
package zebra4j;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import zebra4j.fact.Fact;

/**
 * A puzzle that asks all attributes in {@link #getAttributeSets()} to be
 * assigned to people, satisfying rules defined by the attribute sets used in
 * the puzzle (e.g. all people have different name and the name may be Liza,
 * John or Mary). The assignment must also satisfy the given set of
 * {@link Fact}s.
 */
@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Puzzle {

	private final Map<AttributeType, Set<Attribute>> attributeSets;
	private final Set<Fact> facts;

	/**
	 * @return the number of people in the puzzle
	 */
	public int numPeople() {
		return attributeSets.values().iterator().next().size();
	}

	public List<String> describeConstraints(Locale locale) {
		Stream<String> setDesc = attributeSets.entrySet().stream()
				.map(e -> e.getKey().describeSet(e.getValue(), locale));
		// TODO Use localized Fact.description(locale) instead of toString
		Stream<String> factDesc = facts.stream().map(Fact::toString);
		return Stream.concat(setDesc, factDesc).collect(Collectors.toList());
	}
}
