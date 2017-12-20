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
package org.lorislab.clingo4j.api.ast;

import java.util.Optional;
import org.bridj.Pointer;
import org.lorislab.clingo4j.api.Symbol;
import org.lorislab.clingo4j.api.c.ClingoLibrary.clingo_ast_body_literal_type;
import static org.lorislab.clingo4j.api.c.ClingoLibrary.clingo_ast_body_literal_type.clingo_ast_body_literal_type_aggregate;
import static org.lorislab.clingo4j.api.c.ClingoLibrary.clingo_ast_body_literal_type.clingo_ast_body_literal_type_conditional;
import static org.lorislab.clingo4j.api.c.ClingoLibrary.clingo_ast_body_literal_type.clingo_ast_body_literal_type_disjoint;
import static org.lorislab.clingo4j.api.c.ClingoLibrary.clingo_ast_body_literal_type.clingo_ast_body_literal_type_literal;
import org.lorislab.clingo4j.api.c.ClingoLibrary.clingo_ast_head_literal_type;
import static org.lorislab.clingo4j.api.c.ClingoLibrary.clingo_ast_head_literal_type.clingo_ast_head_literal_type_aggregate;
import static org.lorislab.clingo4j.api.c.ClingoLibrary.clingo_ast_head_literal_type.clingo_ast_head_literal_type_disjunction;
import static org.lorislab.clingo4j.api.c.ClingoLibrary.clingo_ast_head_literal_type.clingo_ast_head_literal_type_head_aggregate;
import static org.lorislab.clingo4j.api.c.ClingoLibrary.clingo_ast_head_literal_type.clingo_ast_head_literal_type_literal;
import static org.lorislab.clingo4j.api.c.ClingoLibrary.clingo_ast_head_literal_type.clingo_ast_head_literal_type_theory_atom;
import org.lorislab.clingo4j.api.c.ClingoLibrary.clingo_ast_literal_type;
import static org.lorislab.clingo4j.api.c.ClingoLibrary.clingo_ast_literal_type.clingo_ast_literal_type_boolean;
import static org.lorislab.clingo4j.api.c.ClingoLibrary.clingo_ast_literal_type.clingo_ast_literal_type_comparison;
import static org.lorislab.clingo4j.api.c.ClingoLibrary.clingo_ast_literal_type.clingo_ast_literal_type_csp;
import static org.lorislab.clingo4j.api.c.ClingoLibrary.clingo_ast_literal_type.clingo_ast_literal_type_symbolic;
import org.lorislab.clingo4j.api.c.ClingoLibrary.clingo_ast_statement_type;
import static org.lorislab.clingo4j.api.c.ClingoLibrary.clingo_ast_statement_type.clingo_ast_statement_type_const;
import static org.lorislab.clingo4j.api.c.ClingoLibrary.clingo_ast_statement_type.clingo_ast_statement_type_edge;
import static org.lorislab.clingo4j.api.c.ClingoLibrary.clingo_ast_statement_type.clingo_ast_statement_type_external;
import static org.lorislab.clingo4j.api.c.ClingoLibrary.clingo_ast_statement_type.clingo_ast_statement_type_heuristic;
import static org.lorislab.clingo4j.api.c.ClingoLibrary.clingo_ast_statement_type.clingo_ast_statement_type_minimize;
import static org.lorislab.clingo4j.api.c.ClingoLibrary.clingo_ast_statement_type.clingo_ast_statement_type_program;
import static org.lorislab.clingo4j.api.c.ClingoLibrary.clingo_ast_statement_type.clingo_ast_statement_type_project_atom;
import static org.lorislab.clingo4j.api.c.ClingoLibrary.clingo_ast_statement_type.clingo_ast_statement_type_project_atom_signature;
import static org.lorislab.clingo4j.api.c.ClingoLibrary.clingo_ast_statement_type.clingo_ast_statement_type_rule;
import static org.lorislab.clingo4j.api.c.ClingoLibrary.clingo_ast_statement_type.clingo_ast_statement_type_script;
import static org.lorislab.clingo4j.api.c.ClingoLibrary.clingo_ast_statement_type.clingo_ast_statement_type_show_signature;
import static org.lorislab.clingo4j.api.c.ClingoLibrary.clingo_ast_statement_type.clingo_ast_statement_type_show_term;
import static org.lorislab.clingo4j.api.c.ClingoLibrary.clingo_ast_statement_type.clingo_ast_statement_type_theory_definition;
import org.lorislab.clingo4j.api.c.ClingoLibrary.clingo_ast_term_type;
import static org.lorislab.clingo4j.api.c.ClingoLibrary.clingo_ast_term_type.clingo_ast_term_type_binary_operation;
import static org.lorislab.clingo4j.api.c.ClingoLibrary.clingo_ast_term_type.clingo_ast_term_type_external_function;
import static org.lorislab.clingo4j.api.c.ClingoLibrary.clingo_ast_term_type.clingo_ast_term_type_function;
import static org.lorislab.clingo4j.api.c.ClingoLibrary.clingo_ast_term_type.clingo_ast_term_type_interval;
import static org.lorislab.clingo4j.api.c.ClingoLibrary.clingo_ast_term_type.clingo_ast_term_type_pool;
import static org.lorislab.clingo4j.api.c.ClingoLibrary.clingo_ast_term_type.clingo_ast_term_type_symbol;
import static org.lorislab.clingo4j.api.c.ClingoLibrary.clingo_ast_term_type.clingo_ast_term_type_unary_operation;
import static org.lorislab.clingo4j.api.c.ClingoLibrary.clingo_ast_term_type.clingo_ast_term_type_variable;
import org.lorislab.clingo4j.api.c.ClingoLibrary.clingo_ast_theory_term_type;
import static org.lorislab.clingo4j.api.c.ClingoLibrary.clingo_ast_theory_term_type.clingo_ast_theory_term_type_function;
import static org.lorislab.clingo4j.api.c.ClingoLibrary.clingo_ast_theory_term_type.clingo_ast_theory_term_type_symbol;
import static org.lorislab.clingo4j.api.c.ClingoLibrary.clingo_ast_theory_term_type.clingo_ast_theory_term_type_unparsed_term;
import static org.lorislab.clingo4j.api.c.ClingoLibrary.clingo_ast_theory_term_type.clingo_ast_theory_term_type_variable;
import org.lorislab.clingo4j.api.c.clingo_ast_aggregate;
import org.lorislab.clingo4j.api.c.clingo_ast_aggregate_guard;
import org.lorislab.clingo4j.api.c.clingo_ast_binary_operation;
import org.lorislab.clingo4j.api.c.clingo_ast_body_aggregate;
import org.lorislab.clingo4j.api.c.clingo_ast_body_aggregate_element;
import org.lorislab.clingo4j.api.c.clingo_ast_body_literal;
import org.lorislab.clingo4j.api.c.clingo_ast_comparison;
import org.lorislab.clingo4j.api.c.clingo_ast_conditional_literal;
import org.lorislab.clingo4j.api.c.clingo_ast_csp_guard;
import org.lorislab.clingo4j.api.c.clingo_ast_csp_literal;
import org.lorislab.clingo4j.api.c.clingo_ast_csp_product_term;
import org.lorislab.clingo4j.api.c.clingo_ast_csp_sum_term;
import org.lorislab.clingo4j.api.c.clingo_ast_definition;
import org.lorislab.clingo4j.api.c.clingo_ast_disjoint;
import org.lorislab.clingo4j.api.c.clingo_ast_disjoint_element;
import org.lorislab.clingo4j.api.c.clingo_ast_disjunction;
import org.lorislab.clingo4j.api.c.clingo_ast_edge;
import org.lorislab.clingo4j.api.c.clingo_ast_external;
import org.lorislab.clingo4j.api.c.clingo_ast_function;
import org.lorislab.clingo4j.api.c.clingo_ast_head_aggregate;
import org.lorislab.clingo4j.api.c.clingo_ast_head_aggregate_element;
import org.lorislab.clingo4j.api.c.clingo_ast_head_literal;
import org.lorislab.clingo4j.api.c.clingo_ast_heuristic;
import org.lorislab.clingo4j.api.c.clingo_ast_id;
import org.lorislab.clingo4j.api.c.clingo_ast_interval;
import org.lorislab.clingo4j.api.c.clingo_ast_literal;
import org.lorislab.clingo4j.api.c.clingo_ast_minimize;
import org.lorislab.clingo4j.api.c.clingo_ast_pool;
import org.lorislab.clingo4j.api.c.clingo_ast_program;
import org.lorislab.clingo4j.api.c.clingo_ast_project;
import org.lorislab.clingo4j.api.c.clingo_ast_rule;
import org.lorislab.clingo4j.api.c.clingo_ast_script;
import org.lorislab.clingo4j.api.c.clingo_ast_show_signature;
import org.lorislab.clingo4j.api.c.clingo_ast_show_term;
import org.lorislab.clingo4j.api.c.clingo_ast_statement;
import org.lorislab.clingo4j.api.c.clingo_ast_term;
import org.lorislab.clingo4j.api.c.clingo_ast_theory_atom;
import org.lorislab.clingo4j.api.c.clingo_ast_theory_atom_definition;
import org.lorislab.clingo4j.api.c.clingo_ast_theory_atom_element;
import org.lorislab.clingo4j.api.c.clingo_ast_theory_definition;
import org.lorislab.clingo4j.api.c.clingo_ast_theory_function;
import org.lorislab.clingo4j.api.c.clingo_ast_theory_guard;
import org.lorislab.clingo4j.api.c.clingo_ast_theory_guard_definition;
import org.lorislab.clingo4j.api.c.clingo_ast_theory_operator_definition;
import org.lorislab.clingo4j.api.c.clingo_ast_theory_term;
import org.lorislab.clingo4j.api.c.clingo_ast_theory_term_array;
import org.lorislab.clingo4j.api.c.clingo_ast_theory_term_definition;
import org.lorislab.clingo4j.api.c.clingo_ast_theory_unparsed_term;
import org.lorislab.clingo4j.api.c.clingo_ast_theory_unparsed_term_element;
import org.lorislab.clingo4j.api.c.clingo_ast_unary_operation;
import org.lorislab.clingo4j.util.ClingoUtil;

/**
 *
 * @author andrej
 */
public class ASTToC {

    public static clingo_ast_statement convStatement(Statement x) {
        clingo_ast_statement t = x.getData().createStatment();
        t.location(x.getLocation());
        return t;
    }
    
    static clingo_ast_id convId(final Id id) {
        clingo_ast_id tmp = new clingo_ast_id();
        tmp.id(Pointer.pointerToCString(id.getId()));
        tmp.location(id.getLocation());
        return tmp;
    }

    private static clingo_ast_term createAstTerm(clingo_ast_term_type type) {
        clingo_ast_term ret = new clingo_ast_term();
        ret.type((int) type.value);
        return ret;
    }

    public static clingo_ast_term visitTerm(final Symbol x) {
        clingo_ast_term ret = createAstTerm(clingo_ast_term_type_symbol);
        ret.field1().symbol(x.getPointer().get());
        return ret;
    }

    static clingo_ast_term visitTerm(final Variable x) {
        clingo_ast_term ret = createAstTerm(clingo_ast_term_type_variable);
        ret.field1().variable(Pointer.pointerToCString(x.getName()));
        return ret;
    }

    static clingo_ast_term visitTerm(final UnaryOperation x) {
        clingo_ast_unary_operation uo = new clingo_ast_unary_operation();
        uo.unary_operator(x.getUnaryOperator().getValue());
        uo.argument(convTerm(x.getArgument()));
        clingo_ast_term ret = createAstTerm(clingo_ast_term_type_unary_operation);
        ret.field1().unary_operation(Pointer.getPointer(uo));
        return ret;
    }

    static clingo_ast_term visitTerm(final BinaryOperation x) {
        clingo_ast_binary_operation bo = new clingo_ast_binary_operation();
        bo.binary_operator(x.getOperator().getValue());
        bo.left(convTerm(x.getLeft()));
        bo.right(convTerm(x.getRight()));
        clingo_ast_term ret = createAstTerm(clingo_ast_term_type_binary_operation);
        ret.field1().binary_operation(Pointer.getPointer(bo));
        return ret;
    }

    static clingo_ast_term visitTerm(final Interval x) {
        clingo_ast_interval i = new clingo_ast_interval();
        i.left(convTerm(x.getLeft()));
        i.right(convTerm(x.getRight()));
        clingo_ast_term ret = createAstTerm(clingo_ast_term_type_interval);
        ret.field1().interval(Pointer.getPointer(i));
        return ret;
    }

    static clingo_ast_term visitTerm(final Function x) {
        clingo_ast_function fn = new clingo_ast_function();
        fn.name(Pointer.pointerToCString(x.getName()));
        fn.arguments(ClingoUtil.createArray(x.getArguments(), clingo_ast_term.class, ASTToC::convTerm));
        fn.size(ClingoUtil.arraySize(x.getArguments()));
        clingo_ast_term ret = createAstTerm(x.isExternal() ? clingo_ast_term_type_external_function : clingo_ast_term_type_function);
        ret.field1().function(Pointer.getPointer(fn));
        return ret;
    }

    static clingo_ast_term visitTerm(final Pool x) {
        clingo_ast_pool pool = new clingo_ast_pool();
        pool.arguments(ClingoUtil.createArray(x.getArguments(), clingo_ast_term.class, ASTToC::convTerm));
        pool.size(ClingoUtil.arraySize(x.getArguments()));
        clingo_ast_term ret = createAstTerm(clingo_ast_term_type_pool);
        ret.field1().pool(Pointer.getPointer(pool));
        return ret;
    }

    static Pointer<clingo_ast_term> convTerm(final Optional<Term> x) {
        return x.isPresent() ? Pointer.getPointer(convTerm(x.get())) : null;
    }

    static clingo_ast_term convTerm(Term x) {
        clingo_ast_term t = x.getData().createTerm();
        t.location(x.getLocation());
        return t;
    }

    static clingo_ast_csp_product_term convCSPProduct(CSPProduct x) {
        clingo_ast_csp_product_term ret = new clingo_ast_csp_product_term();
        ret.location(x.getLocation());
        ret.variable(convTerm(x.getVariable()));
        ret.coefficient(convTerm(x.getCoefficient()));
        return ret;
    }

    static clingo_ast_csp_sum_term convCSPAdd(CSPSum x) {
        clingo_ast_csp_sum_term ret = new clingo_ast_csp_sum_term();
        ret.location(x.getLocation());
        ret.terms(ClingoUtil.createArray(x.getTerms(), clingo_ast_csp_product_term.class, ASTToC::convCSPProduct));
        ret.size(ClingoUtil.arraySize(x.getTerms()));
        return ret;
    }

    static clingo_ast_theory_unparsed_term_element convTheoryUnparsedTermElement(TheoryUnparsedTermElement x) {
        clingo_ast_theory_unparsed_term_element ret = new clingo_ast_theory_unparsed_term_element();
        ret.term(convTheoryTerm(x.getTerm()));
        ret.size(ClingoUtil.arraySize(x.getOperators()));
        ret.operators(ClingoUtil.createListArray(x.getOperators()));
        return ret;
    }

    private static clingo_ast_theory_term createTheoryTerm(clingo_ast_theory_term_type type) {
        clingo_ast_theory_term result = new clingo_ast_theory_term();
        result.type((int) type.value);
        return result;
    }

    public static clingo_ast_theory_term visitTheoryTerm(Symbol term) {
        clingo_ast_theory_term ret = createTheoryTerm(clingo_ast_theory_term_type_symbol);
        ret.field1().symbol(term.getPointer().get());
        return ret;
    }

    static clingo_ast_theory_term visitTheoryTerm(Variable term) {
        clingo_ast_theory_term ret = createTheoryTerm(clingo_ast_theory_term_type_variable);
        ret.field1().variable(Pointer.pointerToCString(term.getName()));
        return ret;
    }

    static clingo_ast_theory_term visitTheoryTerm(TheoryTermSequence term) {
        clingo_ast_theory_term_array a = new clingo_ast_theory_term_array();
        a.terms(ClingoUtil.createArray(term.getTerms(), clingo_ast_theory_term.class, ASTToC::convTheoryTerm));
        a.size(ClingoUtil.arraySize(term.getTerms()));
        clingo_ast_theory_term ret = createTheoryTerm(term.getType().getType());
        ret.field1().set(Pointer.getPointer(a));
        return ret;
    }

    static clingo_ast_theory_term visitTheoryTerm(TheoryFunction term) {
        clingo_ast_theory_function fun = new clingo_ast_theory_function();
        fun.name(Pointer.pointerToCString(term.getName()));
        fun.arguments(ClingoUtil.createArray(term.getArguments(), clingo_ast_theory_term.class, ASTToC::convTheoryTerm));
        fun.size(ClingoUtil.arraySize(term.getArguments()));
        clingo_ast_theory_term ret = createTheoryTerm(clingo_ast_theory_term_type_function);
        ret.field1().function(Pointer.getPointer(fun));
        return ret;
    }

    static clingo_ast_theory_term visitTheoryTerm(TheoryUnparsedTerm term) {
        clingo_ast_theory_unparsed_term result = new clingo_ast_theory_unparsed_term();
        result.elements(ClingoUtil.createArray(term.getElements(), clingo_ast_theory_unparsed_term_element.class, ASTToC::convTheoryUnparsedTermElement));
        result.size(ClingoUtil.arraySize(term.getElements()));
        clingo_ast_theory_term ret = createTheoryTerm(clingo_ast_theory_term_type_unparsed_term);
        ret.field1().unparsed_term(Pointer.getPointer(result));
        return ret;
    }

    static clingo_ast_theory_term convTheoryTerm(TheoryTerm x) {
        clingo_ast_theory_term t = x.getData().createTheoryTerm();
        t.location(x.getLocation());
        return null;
    }

    static clingo_ast_csp_guard convCSPGuard(CSPGuard x) {
        clingo_ast_csp_guard ret = new clingo_ast_csp_guard();
        ret.comparison(x.getComparison().getValue());
        ret.term(convCSPAdd(x.getTerm()));
        return ret;
    }

    private static clingo_ast_literal createLiteral(clingo_ast_literal_type type) {
        clingo_ast_literal r = new clingo_ast_literal();
        r.type((int) type.value);
        return r;
    }

    static clingo_ast_literal visit(Boolean x) {
        clingo_ast_literal ret = createLiteral(clingo_ast_literal_type_boolean);
        ret.field1().boolean$(x.isValue());
        return ret;
    }

    static clingo_ast_literal visit(Term x) {
        clingo_ast_literal ret = createLiteral(clingo_ast_literal_type_symbolic);
        clingo_ast_term t = convTerm(x);
        ret.field1().symbol(Pointer.getPointer(t));
        return ret;
    }

    static clingo_ast_literal visit(Comparison x) {
        clingo_ast_comparison com = new clingo_ast_comparison();
        com.comparison(x.getComparison().getValue());
        com.left(convTerm(x.getLeft()));
        com.right(convTerm(x.getRight()));
        clingo_ast_literal ret = createLiteral(clingo_ast_literal_type_comparison);
        ret.field1().comparison(Pointer.getPointer(com));
        return ret;
    }

    static clingo_ast_literal visit(CSPLiteral x) {
        clingo_ast_csp_literal csp = new clingo_ast_csp_literal();
        csp.term(convCSPAdd(x.getTerm()));
        csp.guards(ClingoUtil.createArray(x.getGuards(), clingo_ast_csp_guard.class, ASTToC::convCSPGuard));
        csp.size(ClingoUtil.arraySize(x.getGuards()));
        clingo_ast_literal ret = createLiteral(clingo_ast_literal_type_csp);
        ret.field1().csp_literal(Pointer.getPointer(csp));
        return ret;
    }

    static clingo_ast_literal convLiteral(Literal x) {
        clingo_ast_literal t = x.getData().createLiteral();
        t.location(x.getLocation());
        return t;
    }

    static Pointer<clingo_ast_aggregate_guard> convAggregateGuard(Optional<AggregateGuard> guard) {
        Pointer<clingo_ast_aggregate_guard> result = null;
        if (guard.isPresent()) {
            clingo_ast_aggregate_guard g = new clingo_ast_aggregate_guard();
            g.comparison(guard.get().getComparison().getValue());
            g.term(convTerm(guard.get().getTerm()));
            result = Pointer.getPointer(g);
        }
        return result;
    }

    static clingo_ast_conditional_literal convConditionalLiteral(ConditionalLiteral x) {
        clingo_ast_conditional_literal ret = new clingo_ast_conditional_literal();
        ret.literal(convLiteral(x.getLiteral()));
        ret.condition(ClingoUtil.createArray(x.getCondition(), clingo_ast_literal.class, ASTToC::convLiteral));
        ret.size(ClingoUtil.arraySize(x.getCondition()));
        return ret;
    }

    static Pointer<clingo_ast_theory_guard> convTheoryGuard(Optional<TheoryGuard> x) {
        Pointer<clingo_ast_theory_guard> result = null;
        if (x.isPresent()) {
            clingo_ast_theory_guard g = new clingo_ast_theory_guard();
            g.operator_name(Pointer.pointerToCString(x.get().getOperatorName()));
            g.term(convTheoryTerm(x.get().getTerm()));
            result = Pointer.getPointer(g);
        }
        return result;
    }

    static clingo_ast_theory_atom_element convTheoryAtomElement(TheoryAtomElement x) {
        clingo_ast_theory_atom_element ret = new clingo_ast_theory_atom_element();
        ret.tuple(ClingoUtil.createArray(x.getTuple(), clingo_ast_theory_term.class, ASTToC::convTheoryTerm));
        ret.tuple_size(ClingoUtil.arraySize(x.getTuple()));
        ret.condition(ClingoUtil.createArray(x.getCondition(), clingo_ast_literal.class, ASTToC::convLiteral));
        ret.condition_size(ClingoUtil.arraySize(x.getCondition()));
        return ret;
    }

    static clingo_ast_body_aggregate_element convBodyAggregateElement(BodyAggregateElement x) {
        clingo_ast_body_aggregate_element ret = new clingo_ast_body_aggregate_element();
        ret.tuple(ClingoUtil.createArray(x.getTuple(), clingo_ast_term.class, ASTToC::convTerm));
        ret.tuple_size(ClingoUtil.arraySize(x.getTuple()));
        ret.condition(ClingoUtil.createArray(x.getCondition(), clingo_ast_literal.class, ASTToC::convLiteral));
        ret.condition_size(ClingoUtil.arraySize(x.getCondition()));
        return ret;
    }

    static clingo_ast_head_aggregate_element convHeadAggregateElement(HeadAggregateElement x) {
        clingo_ast_head_aggregate_element ret = new clingo_ast_head_aggregate_element();
        ret.tuple(ClingoUtil.createArray(x.getTuple(), clingo_ast_term.class, ASTToC::convTerm));
        ret.tuple_size(ClingoUtil.arraySize(x.getTuple()));
        ret.conditional_literal(convConditionalLiteral(x.getCondition()));
        return ret;
    }

    static clingo_ast_aggregate convAggregate(Aggregate x) {
        clingo_ast_aggregate ret = new clingo_ast_aggregate();
        ret.left_guard(convAggregateGuard(x.getLeftGuard()));
        ret.right_guard(convAggregateGuard(x.getRightGuard()));
        ret.elements(ClingoUtil.createArray(x.getElements(), clingo_ast_conditional_literal.class, ASTToC::convConditionalLiteral));
        ret.size(ClingoUtil.arraySize(x.getElements()));
        return ret;
    }

    static clingo_ast_theory_atom convTheoryAtom(TheoryAtom x) {
        clingo_ast_theory_atom ret = new clingo_ast_theory_atom();
        ret.term(convTerm(x.getTerm()));
        ret.guard(convTheoryGuard(x.getGuard()));
        ret.elements(ClingoUtil.createArray(x.getElements(), clingo_ast_theory_atom_element.class, ASTToC::convTheoryAtomElement));
        ret.size(ClingoUtil.arraySize(x.getElements()));
        return ret;
    }

    static clingo_ast_disjoint_element convDisjointElement(DisjointElement x) {
        clingo_ast_disjoint_element ret = new clingo_ast_disjoint_element();
        ret.location(x.getLocation());
        ret.tuple(ClingoUtil.createArray(x.getTuple(), clingo_ast_term.class, ASTToC::convTerm));
        ret.tuple_size(ClingoUtil.arraySize(x.getTuple()));
        ret.term(convCSPAdd(x.getTerm()));
        ret.condition(ClingoUtil.createArray(x.getCondition(), clingo_ast_literal.class, ASTToC::convLiteral));
        ret.condition_size(ClingoUtil.arraySize(x.getCondition()));
        return ret;
    }

    private static clingo_ast_head_literal createAstHeadLiteral(clingo_ast_head_literal_type type) {
        clingo_ast_head_literal ret = new clingo_ast_head_literal();
        ret.type((int) type.value);
        return ret;
    }

    static clingo_ast_head_literal visitHeadLiteral(Literal x) {
        clingo_ast_head_literal ret = createAstHeadLiteral(clingo_ast_head_literal_type_literal);
        ret.field1().literal(Pointer.getPointer(convLiteral(x)));
        return ret;
    }

    static clingo_ast_head_literal visitHeadLiteral(Disjunction x) {
        clingo_ast_disjunction disjunction = new clingo_ast_disjunction();
        disjunction.elements(ClingoUtil.createArray(x.getElements(), clingo_ast_conditional_literal.class, ASTToC::convConditionalLiteral));
        disjunction.size(ClingoUtil.arraySize(x.getElements()));
        clingo_ast_head_literal ret = createAstHeadLiteral(clingo_ast_head_literal_type_disjunction);
        ret.field1().disjunction(Pointer.getPointer(disjunction));
        return ret;
    }

    static clingo_ast_head_literal visitHeadLiteral(Aggregate x) {
        clingo_ast_head_literal ret = createAstHeadLiteral(clingo_ast_head_literal_type_aggregate);
        ret.field1().aggregate(Pointer.getPointer(convAggregate(x)));
        return ret;
    }

    static clingo_ast_head_literal visitHeadLiteral(HeadAggregate x) {
        clingo_ast_head_aggregate head_aggregate = new clingo_ast_head_aggregate();
        head_aggregate.left_guard(convAggregateGuard(x.getLeftGuard()));
        head_aggregate.right_guard(convAggregateGuard(x.getRightGuard()));
        head_aggregate.function(x.getFunction().getValue());
        head_aggregate.elements(ClingoUtil.createArray(x.getElements(), clingo_ast_head_aggregate_element.class, ASTToC::convHeadAggregateElement));
        head_aggregate.size(ClingoUtil.arraySize(x.getElements()));
        clingo_ast_head_literal ret = createAstHeadLiteral(clingo_ast_head_literal_type_head_aggregate);
        ret.field1().head_aggregate(Pointer.getPointer(head_aggregate));
        return ret;
    }

    static clingo_ast_head_literal visitHeadLiteral(TheoryAtom x) {
        clingo_ast_head_literal ret = createAstHeadLiteral(clingo_ast_head_literal_type_theory_atom);
        ret.field1().theory_atom(Pointer.getPointer(convTheoryAtom(x)));
        return ret;
    }

    static clingo_ast_head_literal convHeadLiteral(HeadLiteral lit) {
        clingo_ast_head_literal t = lit.getData().createHeadLiteral();
        t.location(lit.getLocation());
        return t;
    }

    private static clingo_ast_body_literal createAstBodyLiteral(clingo_ast_body_literal_type type) {
        clingo_ast_body_literal ret = new clingo_ast_body_literal();
        ret.type((int) type.value);
        return ret;
    }

    static clingo_ast_body_literal visitBodyLiteral(Literal x) {
        clingo_ast_body_literal ret = createAstBodyLiteral(clingo_ast_body_literal_type_literal);
        ret.field1().literal(Pointer.getPointer(convLiteral(x)));
        return ret;
    }

    static clingo_ast_body_literal visitBodyLiteral(ConditionalLiteral x) {
        clingo_ast_body_literal ret = createAstBodyLiteral(clingo_ast_body_literal_type_conditional);
        ret.field1().conditional(Pointer.getPointer(convConditionalLiteral(x)));
        return ret;
    }

    static clingo_ast_body_literal visitBodyLiteral(Aggregate x) {
        clingo_ast_body_literal ret = createAstBodyLiteral(clingo_ast_body_literal_type_aggregate);
        ret.field1().aggregate(Pointer.getPointer(convAggregate(x)));
        return ret;
    }

    static clingo_ast_body_literal visitBodyLiteral(BodyAggregate x) {
        clingo_ast_body_aggregate body_aggregate = new clingo_ast_body_aggregate();
        body_aggregate.left_guard(convAggregateGuard(x.getLeftGuard()));
        body_aggregate.right_guard(convAggregateGuard(x.getRightGuard()));
        body_aggregate.function(x.getFunction().getValue());
        body_aggregate.size(ClingoUtil.arraySize(x.getElements()));
        body_aggregate.elements(ClingoUtil.createArray(x.getElements(), clingo_ast_body_aggregate_element.class, ASTToC::convBodyAggregateElement));
        clingo_ast_body_literal ret = createAstBodyLiteral(clingo_ast_body_literal_type_disjoint);
        ret.field1().body_aggregate(Pointer.getPointer(body_aggregate));
        return ret;
    }

    static clingo_ast_body_literal visitBodyLiteral(TheoryAtom x) {
        clingo_ast_body_literal ret = createAstBodyLiteral(clingo_ast_body_literal_type_disjoint);
        ret.field1().theory_atom(Pointer.getPointer(convTheoryAtom(x)));
        return ret;
    }

    static clingo_ast_body_literal visitBodyLiteral(Disjoint x) {
        clingo_ast_disjoint disjoint = new clingo_ast_disjoint();
        disjoint.size(x.getElements().size());
        disjoint.elements(ClingoUtil.createArray(x.getElements(), clingo_ast_disjoint_element.class, ASTToC::convDisjointElement));
        clingo_ast_body_literal ret = createAstBodyLiteral(clingo_ast_body_literal_type_disjoint);
        ret.field1().disjoint(Pointer.getPointer(disjoint));
        return ret;
    }

    static clingo_ast_body_literal convBodyLiteral(BodyLiteral x) {
        clingo_ast_body_literal t = x.getData().createBodyLiteral();
        t.location(x.getLocation());
        t.sign(x.getSign().getValue());
        return t;
    }

    static clingo_ast_theory_operator_definition convTheoryOperatorDefinition(TheoryOperatorDefinition x) {
        clingo_ast_theory_operator_definition ret = new clingo_ast_theory_operator_definition();
        ret.type(x.getType().getValue());
        ret.priority(x.getPriority());
        ret.location(x.getLocation());
        ret.name(Pointer.pointerToCString(x.getName()));
        return ret;
    }

    static clingo_ast_theory_term_definition convTheoryTermDefinition(TheoryTermDefinition x) {
        clingo_ast_theory_term_definition ret = new clingo_ast_theory_term_definition();
        ret.location(x.getLocation());
        ret.name(Pointer.pointerToCString(x.getName()));
        ret.operators(ClingoUtil.createArray(x.getOperators(), clingo_ast_theory_operator_definition.class, ASTToC::convTheoryOperatorDefinition));
        ret.size(ClingoUtil.arraySize(x.getOperators()));
        return ret;
    }

    static clingo_ast_theory_guard_definition convTheoryGuardDefinition(TheoryGuardDefinition x) {
        clingo_ast_theory_guard_definition ret = new clingo_ast_theory_guard_definition();
        ret.term(Pointer.pointerToCString(x.getTerm()));
        ret.operators(ClingoUtil.createListArray(x.getOperators()));
        ret.size(ClingoUtil.arraySize(x.getOperators()));
        return ret;
    }

    static clingo_ast_theory_atom_definition convTheoryAtomDefinition(TheoryAtomDefinition x) {
        clingo_ast_theory_atom_definition ret = new clingo_ast_theory_atom_definition();
        ret.name(Pointer.pointerToCString(x.getName()));
        ret.arity(x.getArity());
        ret.location(x.getLocation());
        ret.type(x.getType().getValue());
        ret.elements(Pointer.pointerToCString(x.getElements()));
        if (x.getGuard().isPresent()) {
            ret.guard(Pointer.getPointer(convTheoryGuardDefinition(x.getGuard().get())));
        }
        return ret;
    }

    private static clingo_ast_statement createAstStatement(clingo_ast_statement_type type) {
        clingo_ast_statement ret = new clingo_ast_statement();
        ret.type((int) type.value);
        return ret;
    }

    static clingo_ast_statement visit(Rule x) {
        clingo_ast_rule rule = new clingo_ast_rule();
        rule.head(convHeadLiteral(x.getHead()));
        rule.size(ClingoUtil.arraySize(x.getBody()));
        rule.body(ClingoUtil.createArray(x.getBody(), clingo_ast_body_literal.class, ASTToC::convBodyLiteral));
        clingo_ast_statement ret = createAstStatement(clingo_ast_statement_type_rule);
        ret.field1().rule(Pointer.getPointer(rule));
        return ret;
    }

    static clingo_ast_statement visit(Definition x) {
        clingo_ast_definition definition = new clingo_ast_definition();
        definition.is_default(x.isIsDefault());
        definition.name(Pointer.pointerToCString(x.getName()));
        definition.value(convTerm(x.getValue()));
        clingo_ast_statement ret = createAstStatement(clingo_ast_statement_type_const);
        ret.field1().definition(Pointer.getPointer(definition));
        return ret;
    }

    static clingo_ast_statement visit(ShowSignature x) {
        clingo_ast_show_signature signature = new clingo_ast_show_signature();
        signature.csp(x.isCsp());
        signature.signature(x.getSignature().getSignature());
        clingo_ast_statement ret = createAstStatement(clingo_ast_statement_type_show_signature);
        ret.field1().show_signature(Pointer.getPointer(signature));
        return ret;
    }

    static clingo_ast_statement visit(ShowTerm x) {
        clingo_ast_show_term term = new clingo_ast_show_term();
        term.csp(x.isCsp());
        term.term(convTerm(x.getTerm()));
        term.body(ClingoUtil.createArray(x.getBody(), clingo_ast_body_literal.class, ASTToC::convBodyLiteral));
        term.size(ClingoUtil.arraySize(x.getBody()));
        clingo_ast_statement ret = createAstStatement(clingo_ast_statement_type_show_term);
        ret.field1().show_term(Pointer.getPointer(term));
        return ret;
    }

    static clingo_ast_statement visit(Minimize x) {
        clingo_ast_minimize minimize = new clingo_ast_minimize();
        minimize.weight(convTerm(x.getWeight()));
        minimize.priority(convTerm(x.getPriority()));
        minimize.tuple(ClingoUtil.createArray(x.getTuple(), clingo_ast_term.class, ASTToC::convTerm));
        minimize.tuple_size(ClingoUtil.arraySize(x.getTuple()));
        minimize.body(ClingoUtil.createArray(x.getBody(), clingo_ast_body_literal.class, ASTToC::convBodyLiteral));
        minimize.body_size(ClingoUtil.arraySize(x.getBody()));
        clingo_ast_statement ret = createAstStatement(clingo_ast_statement_type_minimize);
        ret.field1().minimize(Pointer.getPointer(minimize));
        return ret;
    }

    static clingo_ast_statement visit(Script x) {
        clingo_ast_script script = new clingo_ast_script();
        script.type(x.getType().getValue());
        script.code(Pointer.pointerToCString(x.getCode()));
        clingo_ast_statement ret = createAstStatement(clingo_ast_statement_type_script);
        ret.field1().script(Pointer.getPointer(script));
        return ret;
    }

    static clingo_ast_statement visit(Program x) {
        clingo_ast_program program = new  clingo_ast_program();
        program.name(Pointer.pointerToCString(x.getName()));
        program.parameters(ClingoUtil.createArray(x.getParameters(), clingo_ast_id.class ,ASTToC::convId));
        program.size(ClingoUtil.arraySize(x.getParameters()));
        clingo_ast_statement ret = createAstStatement(clingo_ast_statement_type_program);
        ret.field1().program(Pointer.getPointer(program));
        return ret;
    }

    static clingo_ast_statement visit(External x) {
        clingo_ast_external external = new clingo_ast_external();
        external.atom(convTerm(x.getAtom()));
        external.body(ClingoUtil.createArray(x.getBody(), clingo_ast_body_literal.class, ASTToC::convBodyLiteral));
        external.size(ClingoUtil.arraySize(x.getBody()));
        clingo_ast_statement ret = createAstStatement(clingo_ast_statement_type_external);
        ret.field1().external(Pointer.getPointer(external));
        return ret;
    }

    static clingo_ast_statement visit(Edge x) {
        clingo_ast_edge edge = new clingo_ast_edge();
        edge.u(convTerm(x.getU()));
        edge.v(convTerm(x.getV()));
        edge.body(ClingoUtil.createArray(x.getBody(), clingo_ast_body_literal.class, ASTToC::convBodyLiteral));
        edge.size(ClingoUtil.arraySize(x.getBody()));
        clingo_ast_statement ret = createAstStatement(clingo_ast_statement_type_edge);
        ret.field1().edge(Pointer.getPointer(edge));
        return ret;
    }
        
    static clingo_ast_statement visit(Heuristic x) {
        clingo_ast_heuristic heuristic = new clingo_ast_heuristic();
        heuristic.atom(convTerm(x.getAtom()));
        heuristic.bias(convTerm(x.getBias()));
        heuristic.priority(convTerm(x.getPriority()));
        heuristic.modifier(convTerm(x.getModifier()));
        heuristic.body(ClingoUtil.createArray(x.getBody(), clingo_ast_body_literal.class, ASTToC::convBodyLiteral));
        heuristic.size(ClingoUtil.arraySize(x.getBody()));
        clingo_ast_statement ret = createAstStatement(clingo_ast_statement_type_heuristic);
        ret.field1().heuristic(Pointer.getPointer(heuristic));
        return ret;
    }

    static clingo_ast_statement visit(ProjectAtom x) {
        clingo_ast_project project = new clingo_ast_project();
        project.atom(convTerm(x.getAtom()));
        project.body(ClingoUtil.createArray(x.getBody(), clingo_ast_body_literal.class, ASTToC::convBodyLiteral));
        project.size(ClingoUtil.arraySize(x.getBody()));
        clingo_ast_statement ret = createAstStatement(clingo_ast_statement_type_project_atom);
        ret.field1().project_atom(Pointer.getPointer(project));
        return ret;
    }

    static clingo_ast_statement visit(ProjectSignature x) {
        clingo_ast_statement ret = createAstStatement(clingo_ast_statement_type_project_atom_signature);
        ret.field1().project_signature(x.getSignature().getSignature());
        return ret;
    }

    static clingo_ast_statement visit(TheoryDefinition x) {
        clingo_ast_theory_definition theory_definition = new clingo_ast_theory_definition();
        theory_definition.name(Pointer.pointerToCString(x.getName()));
        theory_definition.terms(ClingoUtil.createArray(x.getTerms(), clingo_ast_theory_term_definition.class ,ASTToC::convTheoryTermDefinition));
        theory_definition.terms_size(ClingoUtil.arraySize(x.getTerms()));
        theory_definition.atoms(ClingoUtil.createArray(x.getAtoms(), clingo_ast_theory_atom_definition.class ,ASTToC::convTheoryAtomDefinition));
        theory_definition.atoms_size(ClingoUtil.arraySize(x.getAtoms()));
        clingo_ast_statement ret = createAstStatement(clingo_ast_statement_type_theory_definition);
        ret.field1().theory_definition(Pointer.getPointer(theory_definition));
        return ret;
    }

}
