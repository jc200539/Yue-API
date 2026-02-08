export default [
  { path: '/user', layout: false, routes: [{ path: '/user/login', component: './user/login' }] },
  //
  // { path: '/welcome', icon: 'smile', component: './Welcome', menu: {
  //     name: 'Welcome', // 你希望显示的菜单项文本
  //   },
  // },


  {
    path: '/admin',
    menu: {
      name: '接口管理', // 你希望显示的菜单项文本
    },
    icon: 'crown',
    access: 'canAdmin',
    routes: [
      { icon: 'table', path: '/admin/interface_info', component: './InterfaceInfo', menu: {
          name: '接口管理', // 你希望显示的菜单项文本
        },
      },
      { path: '/admin', redirect: '/admin/sub-page' },
      { path: '/admin/sub-page', name: 'sub-page',component: './Admin' },
    ],
  },

  // {
  //   path: '/',
  //   redirect: '/welcome',
  // },

  { path: '*', layout: false, component: './404' },
];
