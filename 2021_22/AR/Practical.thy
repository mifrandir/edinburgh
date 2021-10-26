theory Practical
  imports
    Main
    HOL.Real
begin

section\<open>Part 1: Propositional and First-Order Proofs (40 marks)\<close>

text\<open>Allowed methods:
  \<^item> rule and rule_tac
  \<^item> drule and drule_tac
  \<^item> erule and erule_tac
  \<^item> frule and frule_tac
  \<^item> cut_tac
  \<^item> assumption\<close>

text\<open>Allowed introduction and elimination rules:\<close>
thm exI exE
thm allI allE spec
thm conjI conjE
thm ccontr excluded_middle
thm notI notE notnotD
thm impI impE mp
thm iffI iffE iffD1 iffD2
thm disjI1 disjI2 disjE

text\<open>All proofs in this part must be procedural (apply-style).\<close>

subsection\<open>Problem 1 (3 marks)\<close>

text\<open>1 mark\<close>
lemma contrapos: "P \<longrightarrow> Q \<Longrightarrow> \<not> Q \<longrightarrow> \<not> P"
  apply (rule impI)
  apply (rule ccontr)
  apply (frule notnotD)
  apply (erule notE)
  apply (drule mp)
  by assumption

 

text\<open>2 marks\<close>
lemma flowers_knights: "((\<exists>x. F x) \<longrightarrow> (\<forall>x. G x)) \<longrightarrow> (\<forall>x y. F x \<longrightarrow> G y)"
  apply (rule impI)
  apply (rule allI)+
  apply (rule impI)
  apply (frule_tac P=F in exI)
  apply (drule mp)
   apply assumption
  apply (rule_tac P=G in spec)
  by assumption
  

subsection\<open>Problem 2 (7 marks)\<close>

text\<open>The portait is not in the golden box.\<close>
lemma not_g: "\<lbrakk>G \<longrightarrow> False\<rbrakk> \<Longrightarrow> \<not>G"
  apply (rule ccontr)
  apply (frule notnotD)
  apply (frule mp)
  by assumption

text\<open>De Morgan's Law.\<close>
lemma dm: "\<not>(P \<or> Q) \<Longrightarrow> \<not>P \<and> \<not>Q"
  apply (cut_tac P=P in excluded_middle)
  apply (rule conjI)
   apply (rule ccontr)
   apply (drule notnotD)
   apply (drule_tac P=P and Q=Q in disjI1)
   apply (erule_tac P="P\<or>Q" and R=False in notE)
   apply assumption
  apply (rule ccontr)
   apply (drule notnotD)
   apply (drule_tac P=P and Q=Q in disjI2)
   apply (erule_tac P="P\<or>Q" in notE)
  by assumption  

text\<open>The portait is not in the silver box.\<close>
lemma not_s: "\<lbrakk>S \<longrightarrow> \<not>(S \<or> G)\<rbrakk> \<Longrightarrow> \<not>S"
  apply (rule ccontr)
  apply (drule notnotD)
  apply (drule_tac P=S in mp)
   apply assumption
  apply (cut_tac P=S and Q=G in dm)
   apply assumption
  apply (drule conjunct1)
  apply (erule_tac P=S in notE)
  by assumption

text\<open>Helper\<close>
lemma disj_other: "\<lbrakk>P \<or> Q; ~P\<rbrakk> \<Longrightarrow> Q"
  apply (rule ccontr)
  apply (drule_tac R="False" in disjE)
    apply (drule_tac P=P and R="False" in notE)
     apply assumption+
   apply (drule_tac P=Q and R="False" in notE)
  by assumption
  
  
text\<open>The portait is in the lead boxx.\<close>
lemma l: "\<lbrakk>G \<longrightarrow> False; S \<longrightarrow> \<not>(S \<or> G);  L \<longrightarrow> (L \<longrightarrow> L); G \<or> S \<or> L\<rbrakk> \<Longrightarrow> L"
  apply (cut_tac S=S and G=G in not_s)
   apply assumption
  apply (cut_tac G=G in not_g)
   apply assumption
  apply (rule ccontr)
  apply (cut_tac P=G and Q="S\<or>L" in disj_other)
    apply assumption+
  apply (cut_tac P=S and Q=L in disj_other)
    apply assumption+
  apply (drule_tac P=L and R="False" in notE)
  by assumption

text\<open>The portait is only in the lead box.\<close>
theorem "\<lbrakk>G \<longrightarrow> False; S \<longrightarrow> \<not>(S \<or> G);  L \<longrightarrow> (L \<longrightarrow> L); G \<or> S \<or> L\<rbrakk> \<Longrightarrow> ~G \<and> ~S \<and> L"
  apply (rule conjI)
   apply (cut_tac G=G in not_g)
    apply assumption+
  apply (rule conjI)
  apply (cut_tac G=G and S=S in not_s)
    apply assumption+
  apply (cut_tac G=G and S=S and L=L in l)
  by assumption+

  

section\<open>Knights and Knaves Problems: (30 marks)\<close>

locale knights_knaves =
    fixes V :: "'a \<Rightarrow> bool"
    fixes G :: "'a \<Rightarrow> bool"
    fixes S :: "nat \<Rightarrow> 'a \<Rightarrow> bool"
  assumes V_iff_not_G: "\<forall>x. V x \<longleftrightarrow> \<not> G x"
      and V_imp_not_S: "\<forall>x. \<forall> y. V x \<longrightarrow> \<not> S y x"
      and G_imp_S: "\<forall>x. \<forall> y. G x \<longrightarrow> S y x"
begin

thm impE

subsection\<open>Problem 3 (4 marks)\<close>
lemma S_imp_G: "\<forall>x. \<forall> y. S y x \<longrightarrow> G x"
  apply (rule allI)+
  apply (cut_tac V_imp_not_S)
  apply (rule impI)
  apply (drule_tac x=x in spec)
  apply (drule_tac x=y in spec)
  apply (rule ccontr)
  apply (cut_tac V_iff_not_G)
  apply (drule_tac x=x in spec)
  apply (drule iffD2)
   apply assumption
  apply (drule mp)
   apply assumption
  apply (drule_tac R="False" in notE)
  apply (drule_tac R="G x" in notE)
  by assumption

lemma not_S_imp_V: "\<forall>x. \<forall> y. \<not> S y x \<longrightarrow> V x"
  apply (rule allI)+
  apply (cut_tac G_imp_S)
  apply (rule impI)
  apply (drule_tac x=x in spec)
  apply (drule_tac x=y in spec)
  apply (drule_tac contrapos)
  apply (drule mp)
   apply assumption
  apply (cut_tac V_iff_not_G)
  apply (drule_tac x=x in spec)
  apply (drule iffD2)
  by assumption

  

  

subsection\<open>Problem 4 (6 marks)\<close>
lemma Mel_and_Zoey: "\<lbrakk>S 1 z = V m; S 1 m = (\<not> V z \<and> \<not> V m)\<rbrakk> \<Longrightarrow> G z \<and> V m"
oops

subsection\<open>Problem 5 (20 marks)\<close>
text\<open>5 marks for formalisation + 15 marks for proof\<close>

lemma Abel_and_Beatrice: False (*formulate and prove your claim*)
oops

end

section\<open>Part 2: Geometry with Order and Signed Areas (60 marks)\<close>

text\<open>Additional allowed methods:
  \<^item> subst, unfold
  \<^item> auto, simp, blast
  \<^item> fast, force, fastforce
  \<^item> presburger
  \<^item> algebra, arith, linarith

  All proofs must now be in structured (Isar) style.
  In this part you are not allowed to use tactics metis, meson, smt.
  You may use sledgehammer, try, try0 but if they suggest metis, meson or smt you should find an alternative proof.
\<close>

subsection\<open>Geometry with Ordered Points (14 marks)\<close>
locale points =
    fixes order :: "'p \<Rightarrow> 'p \<Rightarrow> 'p \<Rightarrow> bool"
  assumes order_CBA: "order A B C \<Longrightarrow> order C B A"
      and order_notBCA: "order A B C \<Longrightarrow> \<not> order B C A"
      and order_distinctAC: "order A B C \<Longrightarrow> A \<noteq> C"
begin

subsubsection\<open>Problem 6 (3 marks)\<close>

(* Formalise and prove the statement *)

text\<open>Line through two points:\<close>
definition line :: "'p \<Rightarrow> 'p \<Rightarrow> 'p set"
  where "A \<noteq> B \<Longrightarrow> line A B = {X. X=A \<or> X=B \<or> order A B X \<or> order A X B \<or> order X A B}"

text\<open>Set of all lines:\<close>
definition Lines :: "'p set set"
  where "Lines = {l. \<exists> C D. l = line C D}"

end

subsubsection\<open>Problem 7 (6 marks)\<close>

locale lines =
  points order
    for order :: "'p \<Rightarrow> 'p \<Rightarrow> 'p \<Rightarrow> bool" +
  assumes A_V:"A \<noteq> B \<Longrightarrow> \<exists>C. order A B C"
      and A_VI:"\<lbrakk>C \<in> line A B; D \<in> line A B; C \<noteq> D\<rbrakk> \<Longrightarrow> A \<in> line C D"
      and unique_line:"A\<noteq>B \<Longrightarrow> \<exists>!l\<in>Lines. A \<in> l \<and> B \<in> l"
      (* Formalise axiom AVII and axiom AVIII *)
begin

subsubsection\<open>Problem 8 (5 marks)\<close>

(* Formalise and prove that given a line, there is a point not on the line *)

end

subsection\<open>Triangle Geometry (26 marks)\<close>

locale triangles =
    fixes \<Delta> :: "'a \<Rightarrow> 'a \<Rightarrow> 'a \<Rightarrow> real" (* \Delta then Ctrl+B \<rightarrow> \<Delta> *)
  assumes axiom0_a: "\<Delta> x y z = \<Delta> y z x"
      and axiom0_b: " - \<Delta> z y x = \<Delta> x y z"
      and axiom2: "x \<noteq> y \<Longrightarrow> \<exists>z. (R::real) = \<Delta> x y z"
      and axiom3_a: "\<Delta> x y z + \<Delta> h z y + \<Delta> z h x + \<Delta> y x h = 0"
      and axiom5: "\<Delta> x y z = 0 \<Longrightarrow> (\<Delta> h x y)*(\<Delta> k x z) = (\<Delta> k x y)*(\<Delta> h x z)"

context triangles begin

subsubsection\<open>Problem 9 (16 marks)\<close>

lemma reverse_order1: "- \<Delta> x z y = \<Delta> x y z"
proof -
  have "\<Delta> x y z = \<Delta> y z x" by (rule axiom0_a)
  also have "\<dots> = - \<Delta> x z y" by (subst axiom0_b) arith
  finally show ?thesis by simp
qed

lemma reverse_order2: "- \<Delta> y x z = \<Delta> x y z"
proof -
  have "\<Delta> x y z = - \<Delta> x z y" by (subst reverse_order1) arith
  also have "\<dots> = - \<Delta> y x z" by (subst axiom0_a) arith
  finally show ?thesis by simp
qed

lemma same_order1: "\<Delta> x y z = \<Delta> z x y"
proof -
  have "\<Delta> x y z = \<Delta> y z x" by (rule axiom0_a)
  also have "\<dots> = \<Delta> z x y" by (rule axiom0_a)
  finally show ?thesis by simp
qed

text\<open>Group similar rules under shared names to make them easier to use:\<close>
lemmas reverse_order = reverse_order2 reverse_order1 axiom0_b
lemmas same_order = same_order1 axiom0_a

lemma pos_order_eq_zero:
  assumes "\<Delta> x y z = 0"
    shows "\<Delta> y x z = 0"
      and "\<Delta> x z y = 0"
      and "\<Delta> y z x = 0"
      and "\<Delta> z y x = 0"
      and "\<Delta> z x y = 0"
proof -
  show "\<Delta> y x z = 0"
  proof -
   have "- \<Delta> y x z = \<Delta> x y z"
      by (rule reverse_order)
    then show ?thesis
      using assms by arith
  qed
next
  show "\<Delta> x z y = 0"
  proof -
   have "- \<Delta> x z y = \<Delta> x y z"
      by (rule reverse_order)
    then show ?thesis
      using assms by arith
  qed
next
  show "\<Delta> y z x = 0"
  proof -
   have " \<Delta> x y z = \<Delta> y z x"
      by (rule axiom0_a)
    then show ?thesis
      by (simp add: assms)
  qed
next
  show "\<Delta> z y x = 0"
  proof -
   have "- \<Delta> z y x = \<Delta> x y z"
      by (rule reverse_order)
    then show ?thesis
      using assms by arith
  qed
next
  show "\<Delta> z x y = 0"
  proof -
   have "\<Delta> z x y = \<Delta> x y z"
      by (rule same_order)
    then show ?thesis
      using assms by arith
  qed
qed

lemma neg_order_eq_zero:
  assumes " \<Delta> x y z = 0"
    shows "-\<Delta> x y z = 0"
      and "-\<Delta> y x z = 0"
      and "-\<Delta> x z y = 0"
      and "-\<Delta> y z x = 0"
      and "-\<Delta> z y x = 0"
      and "-\<Delta> z x y = 0"
proof -
  show "-\<Delta> x y z = 0"
    proof -
      show ?thesis
        using assms by arith
    qed
next
  show "-\<Delta> y x z = 0"
    proof -
   have "-\<Delta> y x z = \<Delta> x y z"
      by (rule reverse_order)
   also have "\<Delta> x y z = 0" using assms by simp
   finally show "-\<Delta> y x z = 0" by simp
    qed
next
  show "-\<Delta> x z y = 0"
    proof -
    have "-\<Delta> x z y = \<Delta> x y z"
       by (rule reverse_order)
   also have "\<Delta> x y z = 0" using assms by simp
   finally show ?thesis by simp
   qed
next
 show "-\<Delta> y z x = 0"
    proof -
    have "\<Delta> y z x = \<Delta> x y z"
       by (rule same_order)
   also have "\<Delta> x y z = 0" using assms by simp
   finally show ?thesis by simp
   qed
next
 show "-\<Delta> z y x = 0"
    proof -
    have "-\<Delta> z y x = \<Delta> x y z"
       by (rule reverse_order)
   also have "\<Delta> x y z = 0" using assms by simp
   finally show ?thesis by simp
   qed
next
 show "-\<Delta> z x y = 0"
    proof -
    have "\<Delta> z x y = \<Delta> x y z"
       by (rule same_order)
   also have "\<Delta> x y z = 0" using assms by simp
   finally show ?thesis by simp
   qed
qed

lemma order_eq_zero:
  assumes "\<Delta> x y z = 0"
    shows " \<Delta> y x z = 0" and " \<Delta> x z y = 0" and " \<Delta> y z x = 0"
      and " \<Delta> z y x = 0" and " \<Delta> z x y = 0"
      and "-\<Delta> x y z = 0" and "-\<Delta> y x z = 0" and "-\<Delta> x z y = 0"
      and "-\<Delta> y z x = 0" and "-\<Delta> z y x = 0" and "-\<Delta> z x y = 0"
  using assms by (rule pos_order_eq_zero neg_order_eq_zero)+

lemma pos_order_neq_zero:
  assumes "\<Delta> x y z \<noteq> 0"
    shows "\<Delta> y x z \<noteq> 0" and "\<Delta> x z y \<noteq> 0" and "\<Delta> y z x \<noteq> 0"
      and "\<Delta> z y x \<noteq> 0" and "\<Delta> z x y \<noteq> 0"
  using assms pos_order_eq_zero by blast+

lemma axiom1:
  assumes " x = y"
    shows "\<Delta> x y z = 0"
  sorry (*Prove this statement.*)

lemma axiom3_b: "\<Delta> x y z = \<Delta> h y z + \<Delta> x h z + \<Delta> x y h"
proof -
  have "\<Delta> x y z + \<Delta> h z y + \<Delta> z h x + \<Delta> y x h = 0" by (rule axiom3_a)
  then have "\<Delta> x y z = - \<Delta> h z y - \<Delta> z h x - \<Delta> y x h" by arith
  also have "\<dots> = \<Delta> h y z - \<Delta> z h x - \<Delta> y x h" by (subst reverse_order1) arith
  also have "\<dots> = \<Delta> h y z + \<Delta> x h z - \<Delta> y x h" using reverse_order by auto
  also have "\<dots> = \<Delta> h y z + \<Delta> x h z + \<Delta> x y h" using reverse_order axiom0_a by auto
  finally show ?thesis by simp
qed


lemma axiom3_c: "\<Delta> x h y + \<Delta> y k x = \<Delta> h y k + \<Delta> k x h"
proof -
  have "\<Delta> x h y = \<Delta> k h y + \<Delta> x k y + \<Delta> x h k" by (rule axiom3_b)
  then have "\<Delta> x h y - \<Delta> x k y = \<Delta> k h y + \<Delta> x h k" by arith
  moreover have "- \<Delta> x k y = \<Delta> y k x" by (rule reverse_order)
  ultimately have "\<Delta> x h y + \<Delta> y k x = \<Delta> k h y + \<Delta> x h k" by arith
  also have "\<dots> = \<Delta> h y k + \<Delta> x h k" by (subst same_order1) arith
  also have "\<dots> = \<Delta> h y k + \<Delta> k x h" by (subst same_order1) arith
  finally show ?thesis by simp
qed

lemma lemma4:
  assumes "\<Delta> x y z = 0"
    shows "\<Delta> x h y + \<Delta> y h z = \<Delta> x h z"
proof -
  have "\<Delta> x y z = \<Delta> h y z + \<Delta> x h z + \<Delta> x y h" by (rule axiom3_b)
  then have "0 = \<Delta> h y z + \<Delta> x h z + \<Delta> x y h" by (simp add: assms)
  then have "- \<Delta> x y h - \<Delta> h y z = \<Delta> x h z" by arith
  then have "\<Delta> x h y - \<Delta> h y z = \<Delta> x h z" by (simp add: reverse_order axiom0_a)
  moreover have " - \<Delta> h y z = \<Delta> y h z" by (rule reverse_order)
  ultimately show ?thesis by arith
qed

lemma two_points1: "\<Delta> x x y = 0"
proof -
  have "\<Delta> x x x + \<Delta> x x x + \<Delta> x x x + \<Delta> x x x = 0" by (rule axiom3_a)
  then have "\<Delta> x x x = 0" by arith
  have "\<Delta> x x y + \<Delta> x y x + \<Delta> y x x + \<Delta> x x x = 0" by (rule axiom3_a)
  from this and `\<Delta> x x x = 0` have "\<Delta> x x y + \<Delta> x y x + \<Delta> y x x = 0" by simp
  moreover have "- \<Delta> x y x = \<Delta> y x x" by (rule reverse_order)
  ultimately have "\<Delta> x x y + \<Delta> x y x - \<Delta> x y x = 0" by arith
  then show "\<Delta> x x y = 0" by arith
qed

lemma two_points2: "\<Delta> x y y = 0"
proof -
  have "\<Delta> x y y = - \<Delta> y y x" by (subst axiom0_b) arith
  also have "\<dots> = 0" by (subst two_points1) arith
  finally show ?thesis by simp
qed

lemma two_points3: "\<Delta> x y x = 0"
proof -
  have "\<Delta> x y x = \<Delta> x x y" by (subst same_order) arith
  also have "\<dots> = 0" by (subst two_points1) arith
  finally show ?thesis by simp
qed

lemmas two_points = two_points1 two_points2 two_points3

lemma a_b_distinct:
  assumes "\<Delta> a b c \<noteq> 0"
    shows " a \<noteq> b"
proof (rule ccontr)
  assume "\<not> a \<noteq> b"
  then have "a = b" by simp
  then have that: "\<Delta> a b c = 0" by (rule axiom1)
  have "\<not> \<Delta> a b c = 0" using assms by simp
  from this and that show False by (rule notE)
qed

lemma axiom6:
  assumes "\<Delta> x y z = 0"
      and "x \<noteq> z"
    shows " \<exists>L. \<forall>h. \<Delta> h x y = L * \<Delta> h x z"
  sorry (*Prove this statement.*)

end

subsubsection\<open>Problem 10 (10 marks)\<close>

text\<open>For all of the following proofs, you may use any previously proven Isabelle lemmas in the theory or its imports.\<close>

type_synonym point = "real * real"

definition xCoord :: "point \<Rightarrow> real"
  where "xCoord P = fst P"

definition yCoord :: "point \<Rightarrow> real"
  where "yCoord P = snd  P"

definition signedArea :: "[point, point, point] \<Rightarrow> real"
where "signedArea a b c = (1/2) *
    ((xCoord b - xCoord a) * (yCoord c - yCoord a)
  - (yCoord b - yCoord a) * (xCoord c - xCoord a))"

lemma signedArea_0_a: "signedArea p q r = signedArea q r p"
proof -
  have "signedArea p q r = (1/2) * ((xCoord q - xCoord p) * (yCoord r - yCoord p)
    - (yCoord q - yCoord p) * (xCoord r - xCoord p))" by (rule signedArea_def)
  also have "... = (1/2) * ((xCoord r - xCoord q) * (yCoord p - yCoord q)
    - (yCoord r - yCoord q) * (xCoord p - xCoord q))" by algebra
  also have "... = signedArea q r p" by (rule signedArea_def[symmetric])
  finally show ?thesis .
qed

lemma signedArea_0_b: "signedArea p q r = - signedArea p r q"
proof -
  have "signedArea p q r = (1/2) * ((xCoord q - xCoord p)*(yCoord r - yCoord p)
    - (yCoord q - yCoord p) * (xCoord r - xCoord p))" by (rule signedArea_def)
  also have "... = - (1/2 * ((xCoord r - xCoord p) * (yCoord q - yCoord p)
    - (yCoord r - yCoord p) * (xCoord q - xCoord p)))" by algebra
  also have "... = - signedArea p r q" by (subst signedArea_def, rule refl)
  finally show ?thesis .
qed

lemma signedArea_2:
  assumes "x \<noteq> y"
    shows "\<exists>z. (R::real) = signedArea x y z"
proof (cases "yCoord x = yCoord y")
  case True
  then show ?thesis
  proof (cases "xCoord x = xCoord y")
    case True
    from this `yCoord x = yCoord y` have "x = y" using xCoord_def yCoord_def
      by (simp add: prod.expand)
    from this assms show ?thesis by contradiction
  next
    case False
    define z where "z = (( xCoord x, (2*R/(xCoord y - xCoord x) + yCoord x))::point)"
    then have xCoord_z_def:"yCoord z = 2*R/(xCoord y - xCoord x) + yCoord x" using yCoord_def
      by force
    from z_def have yCoord_z_def: "xCoord z = xCoord x" using xCoord_def
      by force
    have "signedArea x y z = (1/2) * ((xCoord y - xCoord x) * (yCoord z - yCoord x)
              - (yCoord y - yCoord x) * (xCoord z - xCoord x))" by (rule signedArea_def)
    also have "... = (1/2) * ((xCoord y - xCoord x) * (2*R/(xCoord y - xCoord x) + yCoord x - yCoord x)
              - (yCoord y - yCoord x) * (xCoord x - xCoord x))"
      by (subst xCoord_z_def, subst yCoord_z_def, rule refl)
    also have "... = (xCoord y - xCoord x) * R/(xCoord y - xCoord x)"
      by algebra
    also from False have "... = R" by fastforce
    finally show ?thesis
      by blast
  qed
next
  case False
  define z where "z = (((2*R/(yCoord x - yCoord y) + xCoord x, yCoord x ))::point)"
  then have xCoord_z_def:"xCoord z = 2*R/(yCoord x - yCoord y) + xCoord x" using xCoord_def
    by force
  from z_def have yCoord_z_def: "yCoord z = yCoord x" using yCoord_def
    by force
  have "signedArea x y z = (1/2) *((xCoord y - xCoord x)*(yCoord z - yCoord x)
            - (yCoord y - yCoord x)*(xCoord z - xCoord x))" by (rule signedArea_def)
  also have "... = (1/2) *((xCoord y - xCoord x)*(yCoord x - yCoord x)
            - (yCoord y - yCoord x)*(2*R/(yCoord x - yCoord y) + xCoord x - xCoord x))"
    by (subst xCoord_z_def, subst yCoord_z_def, rule refl)
  also have "... = -(yCoord y - yCoord x)*R/(yCoord x - yCoord y)"
    by algebra
  also from False have "... = R" by fastforce
  finally show ?thesis by blast
qed

lemma signedArea_3_a: "signedArea x y z + signedArea h z y + signedArea z h x + signedArea y x h = 0"
proof -
  have "signedArea x y z + signedArea h z y + signedArea z h x + signedArea y x h =
  (1/2)*((xCoord y - xCoord x)*(yCoord z - yCoord x) - (yCoord y - yCoord x)*(xCoord z - xCoord x))
+ (1/2)*((xCoord z - xCoord h)*(yCoord y - yCoord h) - (yCoord z - yCoord h)*(xCoord y - xCoord h))
+ (1/2)*((xCoord h - xCoord z)*(yCoord x - yCoord z) - (yCoord h - yCoord z)*(xCoord x - xCoord z))
+ (1/2)*((xCoord x - xCoord y)*(yCoord h - yCoord y) - (yCoord x - yCoord y)*(xCoord h - xCoord y))"
    by ((subst signedArea_def)+, rule refl)
  also have "... = 0" by algebra
  finally show ?thesis by blast
qed

(* Formulate and prove signedArea_5 *)



(* Now using the definition of signedArea instantiate the triangles locale
  so that \<Delta> corresponds to  signedArea. Use command 'interpretation'
  (it may be easier to prove the assumptions of the locale separately first). *)

subsection\<open>Problem 11: Challenge (20 marks)\<close>

locale triangles_continuum_pt =
  triangles \<Delta>
    for \<Delta> :: "'a \<Rightarrow> 'a \<Rightarrow> 'a \<Rightarrow> real" +
  assumes "\<exists> (a::'a) b. a \<noteq> b" (* then by axiom 2 we can get a continuum of points *)
begin

(* Define 'order' in terms of \<Delta> and then instantiate the points locale. *)

end
end

