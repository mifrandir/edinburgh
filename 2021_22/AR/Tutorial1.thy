theory Tutorial1
  imports Main
begin

lemma "(P \<longrightarrow> (Q \<longrightarrow> R)) \<longrightarrow> ((P \<longrightarrow> Q) \<longrightarrow> (P \<longrightarrow> R))"
  apply (rule impI)
  apply (rule impI)
  apply (rule impI)
  apply (erule impE)
   apply (erule impE)
    apply assumption
   apply assumption
  apply (erule mp)
  apply (erule mp)
  by assumption

lemma "\<not>\<not>P\<longrightarrow>P"
  apply (rule impI)
  apply (rule ccontr)
  apply (rule notE)
   apply assumption
  by assumption

lemma "(P \<longrightarrow> Q \<and> R) \<longrightarrow> ((P \<longrightarrow> Q) \<and> (P \<longrightarrow> R))" 
  apply (rule impI)
  apply (rule conjI)
   apply (rule impI)
   apply (erule impE)
    apply assumption
   apply (erule conjunct1)
  apply (rule impI)
  apply (erule impE)
   apply assumption
  by (erule conjunct2)

lemma "(~P \<longrightarrow> Q) \<longrightarrow> (~Q \<longrightarrow> P)"
  apply (rule impI)
  apply (rule impI)
  apply (rule ccontr)
  apply (erule impE)
   apply assumption
  apply (rule notE)
   apply assumption
  by assumption

thm excluded_middle
lemma "P | ~P"
  apply (rule ccontr)
  apply (drule excluded_middle)

end