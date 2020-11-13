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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Test;

public class QuestionTest {

	@Test
	public void testGenerate() {
		PuzzleSolution solution = PuzzleGeneratorTest.simpleSolutionWithCriminal();
		Question question = Question.generate(solution.getAttributeSets(), new Random(1));
		assertTrue(String.format("Solution: %s, Question: %s", solution, question), question.appliesTo(solution));
	}

	@Test
	public void testGenerate_Stable() {
		PuzzleSolution solution = PuzzleGeneratorTest.simpleSolutionWithCriminal();
		Question question = Question.generate(solution.getAttributeSets(), new Random(1));
		assertEquals(question, Question.generate(solution.getAttributeSets(), new Random(1)));
	}

}
