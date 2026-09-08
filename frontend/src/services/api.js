const api = {
  get: async (url) => {
    return { data: [] };
  },

  post: async (url, data) => {
    return {
      status: 201,
      data: {
        id: 1,
        ...data
      }
    };
  },

  put: async (url, data) => {
    return {
      status: 200,
      data: {
        id: 1,
        ...data
      }
    };
  },

  delete: async (url) => {
    return {
      data: 'StudyDeck deleted successfully.'
    };
  }
};

export default api;