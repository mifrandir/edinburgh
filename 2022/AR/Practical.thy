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

text\<open>De Morgan's Laws\<close>

lemma dm1: "\<not>(P \<or> Q) \<Longrightarrow> \<not>P \<and> \<not>Q"
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
  apply (cut_tac P=S and Q=G in dm1)
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
  apply (drule_tac P="S y x" and R="False" in notE)
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

lemma iff_flip: "P \<longleftrightarrow> Q \<Longrightarrow> \<not>P \<longleftrightarrow> \<not>Q" 
  apply (rule iffI)
   apply (rule ccontr)
   apply (drule notnotD)
   apply (drule iffD2)
    apply assumption
   apply (rule_tac P=P and R=False in notE)
    apply assumption+
  apply (rule ccontr)
  apply (drule notnotD)
  apply (drule iffD1)
   apply assumption
  apply (rule_tac P=Q and R=False in notE)
  by assumption+

subsection\<open>Problem 4 (6 marks)\<close>

lemma Zoey: "\<lbrakk>S 1 z = V m; S 1 m = (\<not> V z \<and> \<not> V m)\<rbrakk> \<Longrightarrow> G z"
  apply (cut_tac S_imp_G)
  apply (drule_tac x=z in spec)
  apply (drule_tac x=1 in spec)
  apply (rule ccontr)
   apply (cut_tac P="S 1 z" and Q="G z" in contrapos)
    apply assumption
   apply (drule_tac P="~G z" in mp)
    apply assumption
   apply (drule iffD2)
    apply (rule ccontr)
    apply (cut_tac V_iff_not_G)
    apply (drule_tac x=z in spec)
    apply (drule_tac Q="(~ G z)" in iffD2)
     apply assumption
    apply (cut_tac P="S 1 m" and Q="(\<not> V z \<and> \<not> V m)" in iff_flip)
     apply assumption
    apply (drule_tac P="(~ S 1 m)" and Q="~(~ V z \<and> ~ V m)" in iffD2)
     apply (rule ccontr)
     apply (drule notnotD)
     apply (drule_tac conjunct1)
     apply (drule_tac P="V z" and R=False in notE)
      apply assumption+
    apply (cut_tac not_S_imp_V)
    apply (drule_tac x=m in spec)
    apply (drule_tac x=1 in spec)
    apply (drule_tac P="~ S 1 m" in mp)
     apply assumption
  apply (drule_tac P="V m" and R=False in notE)
     apply assumption+
   apply (drule_tac P="S 1 z" and R=False in notE)
  by assumption+

lemma Mel_and_Zoey: "\<lbrakk>S 1 z = V m; S 1 m = (\<not> V z \<and> \<not> V m)\<rbrakk> \<Longrightarrow> G z \<and> V m"
  apply (cut_tac z=z and m=m in Zoey)
    apply assumption+
  apply (rule conjI)
   apply assumption
  apply (cut_tac V_iff_not_G)
  apply (drule_tac x=z in spec)
  apply (cut_tac P="V z" and Q="~ G z" in iff_flip)
   apply assumption
  apply (cut_tac G_imp_S)
  apply (drule_tac x=z in spec)
  apply (drule_tac x=1 in spec)
  apply (drule mp)
  apply assumption
  apply (drule_tac Q="S 1 z" in iffD1)
  by assumption+

subsection\<open>Problem 5 (20 marks)\<close>
text\<open>5 marks for formalisation + 15 marks for proof\<close>

text\<open>The next two proofs are very similar to Zoey and Mel...\<close>
lemma Abel_G: "\<lbrakk>  S 2 a = V b; S 2 b = (G a \<and> G b) \<rbrakk> \<Longrightarrow> G a"
  apply (cut_tac S_imp_G)
  apply (drule_tac x=a in spec)
  apply (drule_tac x=2 in spec)
  apply (rule ccontr)
   apply (cut_tac P="S 2 a" and Q="G a" in contrapos)
    apply assumption
   apply (drule_tac P="~G a" in mp)
    apply assumption
   apply (drule iffD2)
    apply (rule ccontr)
    apply (cut_tac V_iff_not_G)
    apply (drule_tac x=a in spec)
    apply (drule_tac Q="(~ G a)" in iffD2)
     apply assumption
    apply (cut_tac P="S 2 b" and Q="G a \<and> G b" in iff_flip)
  apply assumption
    apply (drule_tac P="(~ S 2 b)" and Q="~(G a \<and> G b)" in iffD2)
     apply (rule ccontr)
     apply (drule notnotD)
    apply (drule_tac conjunct1)
    apply (cut_tac V_iff_not_G)
    apply (drule_tac x=a in spec)
    apply (drule_tac Q="V a" in iffD1)
     apply assumption
     apply (drule_tac P="G a" and R=False in notE)
      apply assumption+
    apply (cut_tac not_S_imp_V)
    apply (drule_tac x=b in spec)
    apply (drule_tac x=2 in spec)
    apply (drule_tac P="~ S 2 b" in mp)
     apply assumption
  apply (drule_tac P="V b" and R=False in notE)
     apply assumption+
   apply (drule_tac P="S 2 a" and R=False in notE)
  by assumption+

lemma Abel_and_Beatrice_G_and_V: "\<lbrakk> S 2 a = V b; S 2 b = (G a \<and> G b) \<rbrakk> \<Longrightarrow> G a \<and> V b"
  apply (cut_tac a=a and b=b in Abel_G)
    apply assumption+
  apply (rule conjI)
   apply assumption
  apply (cut_tac V_iff_not_G)
  apply (drule_tac x=a in spec)
  apply (cut_tac P="V a" and Q="~ G a" in iff_flip)
   apply assumption
  apply (cut_tac G_imp_S)
  apply (drule_tac x=a in spec)
  apply (drule_tac x=2 in spec)
  apply (drule mp)
  apply assumption
  apply (drule_tac Q="S 2 a" in iffD1)
  by assumption+

lemma Abel_and_Beatrice: "\<lbrakk>S 1 a = ((\<exists>x. F x) \<longrightarrow> (\<forall> x. G x)); S 1 b = (\<not>(\<forall>x. \<forall>y. F x \<longrightarrow> G y)); S 2 a = V b; S 2 b = (G a \<and> G b)\<rbrakk> \<Longrightarrow> G a \<and> V b \<and> ~F a \<and> ~F b"
  apply (cut_tac a=a and b=b in Abel_and_Beatrice_G_and_V)
    apply assumption+
  apply (erule_tac P="G a" and Q="V b" in conjE)
  apply (rule conjI)
   apply assumption
  apply (rule conjI)
   apply assumption
  apply (cut_tac V_imp_not_S)
  apply (drule_tac x=b in spec)
  apply (drule_tac x=1 in spec)
  apply (drule_tac P="V b" in mp)
   apply assumption
  apply (cut_tac P="S 1 b" and Q="(\<not> (\<forall>x y. F x \<longrightarrow> G y))" in iff_flip)
   apply assumption
  apply (drule_tac Q="\<not> S 1 b" in iffD1)
   apply assumption
  apply (drule notnotD)
  apply (frule_tac x=a in spec)
  apply (drule_tac x=b in spec)+
  apply (cut_tac P="F b" and Q="G b" in contrapos)
    apply assumption
  apply (cut_tac P="F a" and Q="G b" in contrapos)
   apply assumption
  apply (cut_tac V_iff_not_G)
  apply (drule_tac x=b in spec)
  apply (drule_tac Q="V b" in iffD1)
   apply assumption
  apply (drule_tac P="\<not>G b" in mp)
   apply assumption
  apply (drule_tac P="\<not>G b" in mp)
   apply assumption
  apply (rule conjI)
  by assumption+

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

lemma order_distinctAB: 
  fixes A::'p and B::'p and C::'p
  assumes "order A B C"
  shows "A \<noteq> B"
proof
  assume a: "A=B"
  then have "order B A C" using assms by blast
  then have "order C A B" using order_CBA by blast
  then have "\<not> order A B C" using order_notBCA by blast
  then show False using assms by auto
qed

lemma order_distinctBC:
    fixes A::'p and B::'p and C::'p
    assumes "order A B C"
    shows "B \<noteq> C"
proof
  assume "B = C" 
  then have "order A C B" using assms by blast
  then have "order B C A" using order_CBA by blast
  also have "~order B C A"
    using \<open>B = C\<close> order_distinctAB by blast 
  then show False using calculation by auto
qed

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
      and A_VII: "\<exists> A. \<exists> B. \<exists> C. A \<noteq> B \<and> B \<noteq> C \<and> C \<noteq> A \<and> ~order A B C \<and> ~order B C A \<and> ~order C A B"
      and A_VIII: "\<lbrakk>~(A \<in> line B C); order B C D; order C E A \<rbrakk> \<Longrightarrow> \<exists> F. order A F B \<and> D \<in> line E F"
begin

lemma uniqueness: "[|\<exists>!x. P x; P A; P B|] ==> A = B"
  by blast
  

subsubsection\<open>Problem 8 (5 marks)\<close>

lemma symmetric_line:
  fixes A::'p and B::'p and X::'p
  assumes p1: "X \<in> line A B"
    and p2: "A \<noteq> B"
  shows "X \<in> line B A" 
proof -
  have a: "X=A \<or> X=B \<or> order A B X \<or> order A X B \<or> order X A B"
    using line_def p1 p2 points.order_CBA by fastforce
  have b: "X=B \<or> X=A \<or> order B A X \<or> order B X A \<or> order X B A"
    using order_CBA a by blast 
  show "X \<in> line B A"
    using b p2 points.line_def points_axioms by fastforce
qed

(* Formalise and prove that given a line, there is a point not on the line *)
lemma not_all_on_line: "\<forall> D. \<forall> E. \<exists> F. ~(F \<in> line D E)"
proof (rule ccontr)
  assume assumption: "~(\<forall> D. \<forall> E. \<exists> F. ~(F \<in> line D E))"
  then have "\<exists> D. \<exists> E. \<forall> F. F \<in> line D E" 
     by blast
   then obtain D where "\<exists> E. \<forall> F. F \<in> line D E" 
     by auto
   then obtain E where all_p_on_DE: "\<forall> F. F \<in> line D E" 
     by auto
   obtain A where "\<exists> B. \<exists> C. (A \<noteq> B \<and> B \<noteq> C \<and> C \<noteq> A \<and> ~(order A B C) \<and> ~(order B C A) \<and> ~(order C A B))" 
     using A_VII by auto
   then obtain B where "\<exists> C. (A \<noteq> B \<and> B \<noteq> C \<and> C \<noteq> A \<and> ~(order A B C) \<and> ~(order B C A) \<and> ~(order C A B))"
     by auto
   then obtain C where spec_A_VII: "A \<noteq> B \<and> B \<noteq> C \<and> C \<noteq> A \<and> ~(order A B C) \<and> ~(order B C A) \<and> ~(order C A B)"
     by auto
   have A_on_DE: "A \<in> line D E" 
     using all_p_on_DE by simp
   have B_on_DE: "B \<in> line D E"
     using all_p_on_DE by simp
   have A_and_B_on_AB: "line A B \<in> Lines \<and> A \<in> line A B \<and> B \<in> line A B"
     using Lines_def line_def spec_A_VII by blast 
   have A_and_B_on_DE: "line D E \<in> Lines \<and> A \<in> line D E \<and> B \<in> line D E"
     using Lines_def A_on_DE B_on_DE by blast 
   have "\<exists>!l\<in>Lines. A \<in> l \<and> B \<in> l" using spec_A_VII unique_line by blast
   then have "line D E = line A B"
     using A_and_B_on_AB A_and_B_on_DE by blast 
   then have "C \<in> line A B"
     using all_p_on_DE by auto 
   then have "\<forall> D. \<forall> E. \<exists> F. ~(F \<in> line D E)"
     using order_CBA points.line_def points_axioms spec_A_VII by fastforce    
   then show False using assumption by auto
qed
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
  assumes p: "x = y"
    shows "\<Delta> x y z = 0"
proof -
  have p1: "\<Delta> x x z = \<Delta> x y z" using p by blast  
  have "\<Delta> x x z = - \<Delta> x x z" using reverse_order2 by force
  then have "\<Delta> x x z = 0" by fastforce
  then show "\<Delta> x y z = 0" using p1 by auto
qed

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
  assumes col: "\<Delta> x y z = 0"
      and neq: "x \<noteq> z"
    shows "\<exists>L. \<forall>h. \<Delta> h x y = L * \<Delta> h x z"
proof -
  define a :: real where "a = 1"
  obtain p where "a = \<Delta> x z p" using axiom2 neq by blast
  then have area: "\<Delta> p x z = a" using axiom0_a by blast
  define q :: real where "q = \<Delta> p x y"
  also have q_works: "\<forall>h. \<Delta> h x y = q * \<Delta> h x z"
    using area a_def axiom5 col q_def by simp
  show "\<exists>L. \<forall>h. \<Delta> h x y = L * \<Delta> h x z"
    using q_works by simp 
qed

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

lemma signedArea_5: 
  fixes x::point and y :: point and z :: point
  assumes col: "signedArea x y z = 0"
  shows "(signedArea h x y)*(signedArea k x z) 
    = (signedArea k x y)*(signedArea h x z)"
proof -
  have "signedArea x y z + signedArea h z y + signedArea z h x + signedArea y x h = 0" using signedArea_3_a by simp
  then have "signedArea h z y + signedArea z h x + signedArea y x h = 0" using col by simp
  also have "signedArea h z y = -signedArea h y z"
    using signedArea_0_b by blast
  then have "-signedArea h y z + signedArea z h x + signedArea y x h = 0" using signedArea_0_b
    using calculation by force
  then have "-signedArea h y z + signedArea h x z + signedArea y x h = 0"
    by (simp add: signedArea_0_a)
  then have "-signedArea h y z + signedArea h x z + signedArea y h x = 0"
    sorry
  show ?thesis by sorry
qed 

(* Now using the definition of signedArea instantiate the triangles locale
  so that \<Delta> corresponds to  signedArea. Use command 'interpretation'
  (it may be easier to prove the assumptions of the locale separately first). *)

interpretation signedArea_as_triangles: triangles signedArea
proof
  fix x and y and z
  show "signedArea x y z = signedArea y z x"
    using signedArea_0_a by blast
  fix x and y and z
  show "- signedArea z y x = signedArea x y z"
  proof -
    have "signedArea z y x = - signedArea z x y"
      using signedArea_0_b by blast
    then have "- signedArea z y x = signedArea z x y"
      by auto
    also have "signedArea x y z = signedArea z x y"
      by (simp add: signedArea_0_a)
    then show ?thesis by (simp add: calculation)
  qed
  fix x and y and R
  show "x \<noteq> y \<Longrightarrow> \<exists>z. R = signedArea x y z"
    using signedArea_2 by blast
  fix x and y and z and h
  show "signedArea x y z + signedArea h z y + signedArea z h x + signedArea y x h = 0"
    by (simp add: signedArea_3_a)
  fix x y z h k
  show "signedArea x y z = 0 \<Longrightarrow> (signedArea h x y) * (signedArea k x z) = (signedArea k x y) * (signedArea h x z)"
    using signedArea_5 by auto
qed

subsection\<open>Problem 11: Challenge (20 marks)\<close>

locale triangles_continuum_pt =
  triangles \<Delta>
    for \<Delta> :: "'a \<Rightarrow> 'a \<Rightarrow> 'a \<Rightarrow> real" +
  assumes "\<exists> (a::'a) b. a \<noteq> b" (* then by axiom 2 we can get a continuum of points *)
begin

definition triangles_order :: "'a \<Rightarrow> 'a \<Rightarrow> 'a \<Rightarrow> bool"
  where 
    "triangles_order x y z = (\<Delta> x y z = 0 \<and> x \<noteq> y \<and> (\<forall>p. \<Delta> p x y > 0 \<longrightarrow> (\<Delta> p y z > 0 \<and> \<Delta> p x z > 0)))"

lemma triangles_order_CBA:
  fixes A B C
  assumes "triangles_order A B C"
  shows "triangles_order C B A"
proof -
    have ABC_zero_area: "\<Delta> A B C = 0" using triangles_order_def assms by blast
    then have "\<Delta> C B A = 0"
      using pos_order_neq_zero(4) by blast 
    have ABC_in_order: "\<forall>p. \<Delta> p A B > 0 \<longrightarrow> (\<Delta> p B C > 0 \<and> \<Delta> p A C > 0)"
      using triangles_order_def assms by auto
    have "A \<noteq> B"
      using assms triangles_order_def by auto
    then have "\<exists>z. 1 = \<Delta> A B z"
      using axiom2 by blast
    then obtain P where "1 = \<Delta> A B P"
      by force
    then have "\<Delta> P A B = 1"
      using axiom0_b reverse_order2 by auto
    then have P_pos_areas: "\<Delta> P B C > 0 \<and> \<Delta> P A C > 0"
      by (simp add: ABC_in_order)
    have main_prop: "\<forall>p. \<Delta> p C B > 0 \<longrightarrow> (\<Delta> p B A > 0 \<and> \<Delta> p C A > 0)" 
    proof
      fix Q
      have "(\<Delta> P A B)*(\<Delta> Q A C) = (\<Delta> Q A B)*(\<Delta> P A C)"
        by (simp add: ABC_zero_area axiom5)
      then have qac_eq: "\<Delta> Q A C = (\<Delta> Q A B)*(\<Delta> P A C)"
        by (simp add: \<open>\<Delta> P A B = 1\<close>)
      then show "\<Delta> Q C B > 0 \<longrightarrow> (\<Delta> Q B A > 0 \<and> \<Delta> Q C A > 0)"
      proof (cases "\<Delta> Q C B > 0")
        case False
        show ?thesis
          using False by auto 
      next
        case True
        have "\<Delta> P A C > 0"
          using P_pos_areas by blast
        have "(\<Delta> Q C B)*(\<Delta> P C A) = (\<Delta> P C B)*(\<Delta> Q C A)"
          by (simp add: \<open>\<Delta> C B A = 0\<close> axiom5)
        then have ax3: "(\<Delta> Q C B)*(\<Delta> P C A)/(\<Delta> P C B) = (\<Delta> Q C A)"
          using P_pos_areas pos_order_neq_zero(2) by force
        have "\<Delta> P C A = - \<Delta> P A C"
          using axiom0_b reverse_order2 by presburger
        then have "\<Delta> P C A < 0"
          by (simp add: P_pos_areas)
        then have lhs_neg: "(\<Delta> Q C B)*(\<Delta> P C A) < 0"
          by (simp add: True mult.commute mult_pos_neg2)
        have "\<Delta> P C B = - \<Delta> P B C"
          using axiom0_b reverse_order2 by presburger
        then have "\<Delta> P C B < 0"
          by (simp add: P_pos_areas)
        then have "(\<Delta> Q C B)*(\<Delta> P C A)/(\<Delta> P C B) > 0"
          by (simp add: divide_neg_neg lhs_neg)
        then have "\<Delta> Q C A > 0"
          by (simp add: ax3)
        have "-\<Delta> Q C A = (\<Delta> Q A B)*(\<Delta> P A C)"
          using axiom0_b qac_eq reverse_order2 by auto
        then have "(\<Delta> Q A B)*(\<Delta> P A C) < 0"
          using \<open>0 < \<Delta> Q C A\<close> by linarith
        then have "\<Delta> Q A B < 0"
          using ABC_in_order mult_less_0_iff qac_eq by force
        also have "\<Delta> Q A B = - \<Delta> Q B A"
          by (simp add: reverse_order1)
        then have "\<Delta> Q B A > 0"
          using calculation by auto
        show ?thesis
          by (simp add: \<open>0 < \<Delta> Q B A\<close> \<open>0 < \<Delta> Q C A\<close>)
      qed
    qed
    have "C \<noteq> B"
      using P_pos_areas two_points2 by force
    show ?thesis
      by (simp add: \<open>C \<noteq> B\<close> main_prop triangles_order_def \<open>\<Delta> C B A = 0\<close>)  
  qed

lemma triangles_order_notBCA:
  fixes A B C
  assumes "triangles_order A B C"
  shows "~triangles_order B C A"
proof
  obtain P where "1 = \<Delta> A B P"
    using assms axiom2 triangles_order_def by blast
  have "\<Delta> P A B > 0 \<longrightarrow> (\<Delta> P B C > 0 \<and> \<Delta> P A C > 0)"
    using assms triangles_order_def by auto
  then have "\<Delta> P B C > 0"
    using \<open>1 = \<Delta> A B P\<close> axiom0_b reverse_order2 by auto
  assume "triangles_order B C A"
  have "\<Delta> P B C > 0 \<longrightarrow> (\<Delta> P C A > 0 \<and> \<Delta> P B A > 0)"
    using \<open>triangles_order B C A\<close> triangles_order_def by auto
  then have "\<Delta> P B A > 0"
    by (simp add: \<open>0 < \<Delta> P B C\<close>)
  then have "\<Delta> P A B < 0"
    by (simp add: axiom0_a less_real_def reverse_order2)
  then show False
    using \<open>1 = \<Delta> A B P\<close> axiom0_a by auto
qed

lemma triangles_order_distinctAC:
  fixes A B C
  assumes "triangles_order A B C"
  shows "A \<noteq> C"
proof
  assume "A = C"
  have "triangles_order C B A"
    by (simp add: assms triangles_order_CBA)
  then have def: "\<Delta> C B A = 0 \<and> C \<noteq> B \<and> (\<forall>p. \<Delta> p C B > 0 \<longrightarrow> (\<Delta> p B A > 0 \<and> \<Delta> p C A > 0))"
    using triangles_order_def by force
  then have "C \<noteq> B"
    by blast
  then have "\<exists>p. 1 = \<Delta> C B p"
    using axiom2 by blast
  then obtain P where "1 = \<Delta> C B P"
    by blast
  then have "\<Delta> P C B = 1"
    by (simp add: axiom0_a)
  have "\<forall>p. \<Delta> p C B > 0 \<longrightarrow> (\<Delta> p B A > 0 \<and> \<Delta> p C A > 0)"
    using def by auto
  then have gtz: "\<Delta> P C A > 0"
    by (simp add: \<open>\<Delta> P C B = 1\<close>)
  have eqz: "\<Delta> P C A = 0"
    by (simp add: \<open>A = C\<close> two_points2)
  show False
    using eqz gtz by auto
qed
    
interpretation points triangles_order
  using points_def triangles_order_CBA triangles_order_distinctAC triangles_order_notBCA by blast

end


end

