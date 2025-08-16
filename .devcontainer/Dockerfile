# ベースに JDK + Maven が入った DevContainer イメージを使用
FROM mcr.microsoft.com/vscode/devcontainers/java:17

# 作業ディレクトリ
WORKDIR /workspace

# git と必要ツールをインストール
RUN apt-get update && \
    apt-get install -y git curl unzip && \
    rm -rf /var/lib/apt/lists/*

# Maven Wrapper を使う場合は権限を確認
RUN [ -f /workspace/mvnw ] && chmod +x /workspace/mvnw || echo "mvnw not found"
