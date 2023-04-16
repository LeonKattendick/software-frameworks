export const getBaseUrl = () => {
  return process.env.NODE_ENV === 'development' ? 'http://localhost:8090' : 'http://backend:8090';
};
