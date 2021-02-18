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
package zebra4j.fact;

import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.List;

import org.junit.Test;

import zebra4j.LocalizationTestUtils;
import zebra4j.PersonName;
import zebra4j.Puzzle;
import zebra4j.PuzzleGeneratorTest;
import zebra4j.PuzzleSolution;
import zebra4j.PuzzleSolver;

public class BothTrueTest {

	@Test
	public void testGenerate() {
		testGenerate(BothTrue.TYPE);
	}

	@Test
	public void testPostTo() {
		testPostTo(BothTrue.TYPE);
	}

	@Test
	public void testDescribe() {
		Fact f = new BothTrue(PersonName.GEORGE, PersonName.ELENA);
		LocalizationTestUtils.testDescribe(f::describe);
	}

	public static void testGenerate(Fact.Type type) {
		PuzzleSolution solution = PuzzleGeneratorTest.simpleSolutionWithCriminal();
		List<Fact> facts = type.generate(solution);
		assertTrue(!facts.isEmpty());
		facts.stream().forEach(f -> assertTrue(f.appliesTo(solution)));
	}

	public static void testPostTo(Fact.Type type) {
		// must not contain Criminal
		PuzzleSolution solution = PuzzleGeneratorTest.sampleSolution();
		List<Fact> facts = type.generate(solution);
		assertTrue(!facts.isEmpty());
		facts.stream().forEach(fact -> testPostTo(fact, solution));
	}

	public static void testPostTo(Fact fact, PuzzleSolution solution) {
		assertTrue(fact.appliesTo(solution));
		Puzzle puzzle = new Puzzle(solution.getAttributeSets(), Collections.singleton(fact));
		new PuzzleSolver(puzzle).solve().stream().forEach(
				anySolution -> assertTrue(fact + " \n " + anySolution.toString(), fact.appliesTo(anySolution)));
	}
}
