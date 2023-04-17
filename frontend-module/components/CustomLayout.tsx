import { Layout } from 'antd';

const CustomLayout = ({ children }: React.PropsWithChildren) => {
  return (
    <Layout style={{ height: '100vh' }}>
      <Layout.Header style={{ backgroundColor: '#141414' }}>MOBA Tracker</Layout.Header>
      <Layout.Content style={{ height: '100%', padding: 24, color: 'white' }}>{children}</Layout.Content>
    </Layout>
  );
};

export default CustomLayout;
