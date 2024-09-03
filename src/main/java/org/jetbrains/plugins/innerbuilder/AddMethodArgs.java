package org.jetbrains.plugins.innerbuilder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;

public class AddMethodArgs {

    @Nullable
    private PsiElement prev;

    @NotNull
    private PsiMethod newMethod;

    @Nullable
    private PsiElement next;

    @NotNull
    private Boolean replace;

    AddMethodArgs(Builder builder) {
        this.prev = builder.prev;
        this.newMethod = builder.newMethod;
        this.next = builder.next;
        this.replace = builder.replace;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Builder toBuilder() {
        Builder builder = new Builder();
        builder.prev = this.prev;
        builder.newMethod = this.newMethod;
        builder.next = this.next;
        builder.replace = this.replace;
        return builder;
    }

    public @Nullable PsiElement getPrev() {
        return this.prev;
    }

    public @NotNull PsiMethod getNewMethod() {
        return this.newMethod;
    }

    public @Nullable PsiElement getNext() {
        return this.next;
    }

    public @NotNull Boolean getReplace() {
        return this.replace;
    }

    public static final class Builder {

        private @Nullable PsiElement prev;

        private @NotNull PsiMethod newMethod;

        private @Nullable PsiElement next;

        private @NotNull Boolean replace;

        Builder() {
        }

        public Builder prev(@Nullable PsiElement prev) {
            this.prev = prev;
            return this;
        }

        public Builder newMethod(@NotNull PsiMethod newMethod) {
            this.newMethod = newMethod;
            return this;
        }

        public Builder next(@Nullable PsiElement next) {
            this.next = next;
            return this;
        }

        public Builder replace(@NotNull Boolean replace) {
            this.replace = replace;
            return this;
        }

        public AddMethodArgs build() {
            return new AddMethodArgs(this);
        }
    }
}
