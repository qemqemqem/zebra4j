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

import lombok.Value;

@Value
public class AtHouse implements Attribute {

	public static AttributeType TYPE = new AllDifferentType() {

		@Override
		public Attribute fromUniqueInt(int input) {
			return AtHouse.fromUniqueInt(input);
		}

		@Override
		public String questionSentencePart() {
			return "В коя къща е";
		}

	};

	private final int house;

	@Override
	public String description() {
		return "в къща " + house;
	}

	@Override
	public int asUniqueInt() {
		return house;
	}

	public static AtHouse fromUniqueInt(int input) {
		return new AtHouse(input);
	}

	@Override
	public AttributeType type() {
		return TYPE;
	}

}
