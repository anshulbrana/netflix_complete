const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function(app) {
  app.use(createProxyMiddleware('/javanetflix', { target: 'http://localhost:8070/netflix/',changeOrigin: true, pathRewrite: { '^/javanetflix': '' } }));
  app.use(createProxyMiddleware('/nodenetflix', { target: 'http://127.0.0.1:4500/api/', changeOrigin: true, pathRewrite: { '^/nodenetflix': '' } }));
};