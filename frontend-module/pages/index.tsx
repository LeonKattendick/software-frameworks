import { useGetAllPlayers } from '@/util/hooks/useGetAllPlayers';
import { Button, Layout } from 'antd';

const Home = () => {
  const { players } = useGetAllPlayers();

  return (
    <Layout style={{ height: '100vh' }}>
      <Layout.Header style={{ backgroundColor: '#141414' }}>MOBA Tracker</Layout.Header>
      <Layout.Content style={{ height: '100%', padding: 24 }}>
        <Button>test</Button>
      </Layout.Content>
    </Layout>
  );
};

export default Home;
