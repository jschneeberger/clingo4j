/*
 * Copyright 2017 andrej.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.lorislab.clingo4j.api;

import java.util.List;
import org.bridj.Pointer;
import static org.lorislab.clingo4j.api.Clingo.LIB;
import static org.lorislab.clingo4j.api.Clingo.handleError;
import org.lorislab.clingo4j.api.ast.Literal;
import org.lorislab.clingo4j.api.ast.Literal.LiteralList;
import org.lorislab.clingo4j.api.c.ClingoLibrary.clingo_propagate_control;

/**
 *
 * @author andrej
 */
public class PropagateControl {

    private final Pointer<clingo_propagate_control> pointer;

    public PropagateControl(Pointer<clingo_propagate_control> pointer) {
        this.pointer = pointer;
    }

    public Pointer<clingo_propagate_control> getPointer() {
        return pointer;
    }

    public int threadId() {
        return LIB.clingo_propagate_control_thread_id(pointer);
    }

    public Assignment assignment() {
        return new Assignment(LIB.clingo_propagate_control_assignment(pointer));
    }

    public int addLiteral() throws ClingoException {
        Pointer<Integer> ret = Pointer.allocateInt();
        handleError(LIB.clingo_propagate_control_add_literal(pointer, ret), "Error add the literal to the propagate control!");
        return ret.getInt();
    }

    public void add_watch(int literal) throws ClingoException {
        handleError(LIB.clingo_propagate_control_add_watch(pointer, literal), "Error add the watch to the propagete control!");
    }

    public boolean hasWatch(int literal) {
        return LIB.clingo_propagate_control_has_watch(pointer, literal);
    }

    public void removeWatch(int literal) {
        LIB.clingo_propagate_control_remove_watch(pointer, literal);
    }

    public boolean add_clause(List<Integer> clause, ClauseType type) throws ClingoException {
        LiteralList list = Literal.toLiteralList(clause);
        Pointer<Boolean> ret = Pointer.allocateBoolean();
        handleError(LIB.clingo_propagate_control_add_clause(pointer, list.getPointer(), list.size(), type.getValue(), ret), "Error add the clause to the propagete control!");
        return ret.get();
    }

    public boolean propagate() throws ClingoException {
        Pointer<Boolean> ret = Pointer.allocateBoolean();
        handleError(LIB.clingo_propagate_control_propagate(pointer, ret), "Error propagete the propagete control!");
        return ret.get();
    }

}