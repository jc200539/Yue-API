/**
 * @see https://umijs.org/docs/max/access#access
 * */
// export default function access(
//   initialState: { currentUser?: API.CurrentUser } | undefined,
// ) {
//   const { currentUser } = initialState ?? {};
//   return {
//     canAdmin: currentUser && currentUser.access === 'admin',
//   };
// }
export default function access(initialState: InitialState | undefined) {
  const { loginUser } = initialState ?? {};
  return {
    canUser: loginUser,
    canAdmin: loginUser?.userRole === 'admin',
  };
}
